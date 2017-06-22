package com.shhetri.controller;

import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.model.Product;
import com.shhetri.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller("productController")
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "admin/product/create";
    }

    @PostMapping("")
    public String store(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/product/create";
        }

        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "Product added successfully.");

        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) throws ModelNotFoundException {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);

        return "admin/product/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute("product") Product product, BindingResult result, RedirectAttributes redirectAttributes) throws ModelNotFoundException {
        if (result.hasErrors()) {
            return "admin/product/edit";
        }

        productService.updateProduct(product, id);
        redirectAttributes.addFlashAttribute("success", "Successfully updated the product information");

        return "redirect:/home";
    }
}
