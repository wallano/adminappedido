package com.restaurante.harley.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.imageio.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.restaurante.harley.dao.CategorieDAO;
import com.restaurante.harley.dao.ClientDAO;
import com.restaurante.harley.dao.InventoryDAO;
import com.restaurante.harley.dao.OrderDAO;
import com.restaurante.harley.dao.ProductDAO;
import com.restaurante.harley.dao.SupplierDAO;
import com.restaurante.harley.entity.Categorie;
import com.restaurante.harley.entity.Product;
import com.restaurante.harley.model.CartInfo;
import com.restaurante.harley.model.CategorieInfo;
import com.restaurante.harley.model.ClientInfo;
import com.restaurante.harley.model.CustomerInfo;
import com.restaurante.harley.model.InventoryInfo;
import com.restaurante.harley.model.PaginationResult;
import com.restaurante.harley.model.ProductInfo;
import com.restaurante.harley.model.SupplierInfo;
import com.restaurante.harley.util.Utils;
import com.restaurante.harley.validator.CustomerInfoValidator;

@Controller
public class MainController {
	
	@Autowired
    private OrderDAO orderDAO;
 
    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private CategorieDAO categorieDAO;
    
    @Autowired
    private SupplierDAO supplierDAO;
    
    @Autowired
    private ClientDAO clientDAO;
    
    @Autowired
    private InventoryDAO inventoryDAO;
 
    @Autowired
    private CustomerInfoValidator customerInfoValidator;
    
    final static String IMAGE_RESOURCE_PATH_CATEGORIES = "/src/main/webapp/resource/images/categories/";
    
    final static String IMAGE_RESOURCE_PATH_PRODUCTS = "/src/main/webapp/resource/images/products/";
 
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        if (target.getClass() == CartInfo.class) {
 
        }

        else if (target.getClass() == CustomerInfo.class) {
            dataBinder.setValidator(customerInfoValidator);
        }
 
    }
 
    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }
 
    @RequestMapping("/")
    public String home() {
        return "index";
    }
 
    @RequestMapping({ "/productList" })
    public String listProductHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
 
        PaginationResult<ProductInfo> result = productDAO.queryProducts(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationProducts", result);
        return "productList";
    }
    
    @RequestMapping({ "/categorieList" })
    public String listCategorieHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
 
        PaginationResult<CategorieInfo> result = categorieDAO.queryCategories(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationCategories", result);
        return "categorieList";
    }
    
    @RequestMapping(value = "/categorieListApp", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    public @ResponseBody String getAllCategoriesJSON(Model model) 
    {
        List<CategorieInfo> categories = categorieDAO.listCategories();
        Gson gson = new Gson();
        String json = gson.toJson(categories);
        System.out.println(json);
        return "categorieList";
    }
    
    @RequestMapping({ "/supplierList" })
    public String listSupplierHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
 
        PaginationResult<SupplierInfo> result = supplierDAO.querySuppliers(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationSuppliers", result);
        return "supplierList";
    }
    
    @RequestMapping(value = "/supplierListApp", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    public @ResponseBody String getAllSuppliersJSON(Model model) 
    {
        List<SupplierInfo> suppliers = supplierDAO.listSuppliers();
        Gson gson = new Gson();
        String json = gson.toJson(suppliers);
        System.out.println(json);
        return "supplierList";
    }
    
    @RequestMapping({ "/clientList" })
    public String listClientHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
 
        PaginationResult<ClientInfo> result = clientDAO.queryClients(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationClients", result);
        return "clientList";
    }
    
    @RequestMapping(value = "/clientListApp", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    public @ResponseBody String getAllClientsJSON(Model model) 
    {
        List<ClientInfo> clients = clientDAO.listClients();
        Gson gson = new Gson();
        String json = gson.toJson(clients);
        System.out.println(json);
        return "clientList";
    }
    
    @RequestMapping({ "/inventoryList" })
    public String listInventoryHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
 
        PaginationResult<InventoryInfo> result = inventoryDAO.queryInventories(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationInventories", result);
        return "inventoryList";
    }
    
    @RequestMapping({ "/buyProduct" })
    public String listProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
 
        Product product = null;
        if (code != null && code.length() > 0) {
            product = productDAO.findProduct(code);
        }
        if (product != null) {
 
 
            CartInfo cartInfo = Utils.getCartInSession(request);
 
            ProductInfo productInfo = new ProductInfo(product);
 
            cartInfo.addProduct(productInfo, 1);
        }

        return "redirect:/shoppingCart";
    }
 
    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
        Product product = null;
        if (code != null && code.length() > 0) {
            product = productDAO.findProduct(code);
        }
        if (product != null) {
 

            CartInfo cartInfo = Utils.getCartInSession(request);
 
            ProductInfo productInfo = new ProductInfo(product);
 
            cartInfo.removeProduct(productInfo);
 
        }

        return "redirect:/shoppingCart";
    }
 
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("cartForm") CartInfo cartForm) {
 
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
 
        return "redirect:/shoppingCart";
    }
 
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);
 
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }
 
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
 
        CartInfo cartInfo = Utils.getCartInSession(request);
      
        if (cartInfo.isEmpty()) {
             
            return "redirect:/shoppingCart";
        }
 
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        if (customerInfo == null) {
            customerInfo = new CustomerInfo();
        }
 
        model.addAttribute("customerForm", customerInfo);
 
        return "shoppingCartCustomer";
    }
 
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("customerForm") @Validated CustomerInfo customerForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
  
        if (result.hasErrors()) {
            customerForm.setValid(false);
            return "shoppingCartCustomer";
        }
 
        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        cartInfo.setCustomerInfo(customerForm);
 
        return "redirect:/shoppingCartConfirmation";
    }
 
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        if (cartInfo.isEmpty()) {
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            return "redirect:/shoppingCartCustomer";
        }
 
        return "shoppingCartConfirmation";
    }
 
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        if (cartInfo.isEmpty()) {
             return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            return "redirect:/shoppingCartCustomer";
        }
        try {
            orderDAO.saveOrder(cartInfo);
        } catch (Exception e) {
        	e.printStackTrace();
            // Need: Propagation.NEVER?
            return "shoppingCartConfirmation";
        }
        Utils.removeCartInSession(request);
         
        Utils.storeLastOrderedCartInSession(request, cartInfo);
 
        return "redirect:/shoppingCartFinalize";
    }
 
    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {
 
        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
 
        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
 
        return "shoppingCartFinalize";
    }
 
    @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("code") String code) throws IOException {
        ProductInfo productInfo = null;
        if (code != null) {
            productInfo = this.productDAO.findProductInfo(code);
            if (productInfo != null && productInfo.getPath() != null) {
                Path path = Paths.get(productInfo.getPath());
                byte[] imageBytes = Files.readAllBytes(path);
                response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
                response.getOutputStream().write(imageBytes); 
              }
        }
//        if (product != null && product.getImage() != null) {
//            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//            response.getOutputStream().write(product.getImage());
//        }
        response.getOutputStream().close();
    }
    
    @RequestMapping(value = { "/categorieImage" }, method = RequestMethod.GET)
    public void categorieImage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("code") String code) throws IOException {
        CategorieInfo categorieInfo = null;
        String relativePath = System.getProperty("user.dir");
        //String absolutePath = relativePath + IMAGE_RESOURCE_PATH_CATEGORIES;
                        
        if (code != null) {
        	categorieInfo = this.categorieDAO.findCategorieInfo(code);
        	if (categorieInfo != null && categorieInfo.getPath() != null) {
              Path path = Paths.get(categorieInfo.getPath());
              byte[] imageBytes = Files.readAllBytes(path);
              response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
              response.getOutputStream().write(imageBytes); 
            }
        }

        response.getOutputStream().close();
    }

}
