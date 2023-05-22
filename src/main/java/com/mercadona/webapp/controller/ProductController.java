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
     *
     * @param model
     * @return String
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

    //Show the product register form
    @GetMapping("/products/add")
    public String addProduct(Model model) {
        List<Category> categories = categoryService.getAll();

        model.addAttribute("categories", categories);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    //Register a new product
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

    @GetMapping("/products/delete/{id}")
        public String deleteProduct(@PathVariable("id") long id){
            productService.deleteProduct(id);
            return "redirect:/products";
        }

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

