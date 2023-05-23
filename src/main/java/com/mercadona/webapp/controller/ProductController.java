package com.mercadona.webapp.controller;

import com.mercadona.webapp.model.Category;
import com.mercadona.webapp.model.Product;
import com.mercadona.webapp.model.User;
import com.mercadona.webapp.service.CategoryServiceImpl;
import com.mercadona.webapp.service.ProductServiceImpl;
import com.mercadona.webapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    /**
     * show the template of the list of products
     * @param model
     * @return String : template of the list of products
     */
    @GetMapping("/products")
    public String getProducts(Model model){
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAll();
        productService.checkDiscountDate(products);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "products";
    }

    /**
     * Show the product register form
     * @param model
     * @return String : template of product register form
     */
    @GetMapping("/products/add")
    public String addProduct(Model model) {
        List<Category> categories = categoryService.getAll();

        model.addAttribute("categories", categories);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    /**
     * Add a product
     * @param newCategory Category object of a possible new category
     * @param product The new product to save
     * @param file MutlipartFile
     * @param model
     * @param auth
     * @return String template : list of products if the product is registered, else the create product form
     * @throws IOException
     */
    @PostMapping("/products/adding")
        public String addingProducts(@ModelAttribute("newCategory") Category newCategory,
                                     @ModelAttribute("product") Product product,
                                     @RequestParam("file") MultipartFile file,
                                     Model model, Authentication auth) throws IOException {
            //If a new Category is created

            if(!newCategory.getNameCategory().equals("")) {
                //Check if category name already exist
                if(categoryService.isCategoryNameExist(newCategory.getNameCategory())) {
                    model.addAttribute("categoryExist", "Cette catégorie existe déjà!");
                    List<Category> categories = categoryService.getAll();
                    model.addAttribute("categories", categories);
                    model.addAttribute("newCategory", new Category());
                    model.addAttribute("product", new Product());
                    return "addProduct";
                }
                //Register the new category and add it to product with the connected user data
                categoryService.saveCategory(newCategory);
                Category categorySaved = categoryService.findCategoryByNameCategory(newCategory.getNameCategory());
                product.setCategory(categorySaved);
                User connectedUser = userService.getUserByPseudo(auth.getName());
                product.setUser(connectedUser);
                productService.saveProduct(file, product);
                return "redirect:/products";
            }
            //If an already created category is selected
            User connectedUser = userService.getUserByPseudo(auth.getName());
            product.setUser(connectedUser);
            productService.saveProduct(file, product);
            return "redirect:/products";
        }

    /**
     * Show the edit product form
     * @param id the id of the current product to edit
     * @param model
     * @return String template : list of products if the product is registered, else the edit product form
     */
    @GetMapping("/products/edit/{id}")
    public String showEditProduct(@PathVariable("id") long id,
                                  Model model){
        List<Category> categories = categoryService.getAll();
        Product product = productService.getProductById(id).get();
        Category newCategory = new Category();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        model.addAttribute("newCategory", newCategory);
        return "editProduct";
    }

    /**
     * Delete the selected product
     * @param id the id of the current product to delete
     * @return String template : list of products
     */
    @GetMapping("/products/delete/{id}")
        public String deleteProduct(@PathVariable("id") long id){
            productService.deleteProduct(id);
            return "redirect:/products";
        }

    /**
     * Edit a product
     * @param id
     * @param newCategory
     * @param product
     * @param file
     * @param model
     * @return String template : list of products if the product is registered, else the edit product form
     * @throws IOException
     */
    @PostMapping("/products/editing/{id}")
    public String EditProduct(@PathVariable("id") long id,
                              @ModelAttribute("newCategory") Category newCategory,
                              @ModelAttribute("product") Product product,
                              @RequestParam("file") MultipartFile file,
                              Model model) throws IOException{
        //If a new Category is created
        if(!newCategory.getNameCategory().equals("")) {
            //Check if category name already exist
            if(categoryService.isCategoryNameExist(newCategory.getNameCategory())) {
                model.addAttribute("categoryExist", "Cette catégorie existe déjà!");
                return "redirect:/products";
            }
            //Register the new category and add it to product with the connected user data
            categoryService.saveCategory(newCategory);
            product.setCategory(categoryService.findCategoryByNameCategory(newCategory.getNameCategory()));
            if(file.isEmpty()) {
                productService.editProduct(product);
                return "redirect:/products";
            }
            productService.editProductWithImage(file, product);
            return "redirect:/products";
        }
        if(file.isEmpty()) {
            productService.editProduct(product);
            return "redirect:/products";
        }
        //If an already created category is selected
        productService.editProductWithImage(file, product);
        return "redirect:/products";
    }


}

