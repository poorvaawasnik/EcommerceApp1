package com.example.E_commerce.App.controller;

import com.example.E_commerce.App.entity.CategoryEntity;
import com.example.E_commerce.App.entity.ProductEntity;
import com.example.E_commerce.App.entity.User;
import com.example.E_commerce.App.service.CategoryService;
import com.example.E_commerce.App.service.ProductService;
import com.example.E_commerce.App.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Value("${upload.path}")
    String uploadpath;
    @Value("${userupload.path}")
    String useruploadpath;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;


    @RequestMapping("/product/{category}")

    public String productByCategory(@PathVariable("category")String category,Model model,HttpSession session){
        CategoryEntity categoryEntity=categoryService.getCategoryByName(category);
        List<ProductEntity> productEntityList = productService.getProductByCategory(categoryEntity);
        List<CategoryEntity>categoryEntityList=categoryService.getAllCategory();
        model.addAttribute("productEntityList",productEntityList);
        model.addAttribute("category",category);
        model.addAttribute("categoryEntityList",categoryEntityList);
        String username=(String) session.getAttribute("username");
        if(username != null)
            model.addAttribute("username",username);
        return "product";

    }
    @RequestMapping("")
    public String index(Model model, HttpSession session) {
        List<ProductEntity> productEntityList=productService.getLatestProduct();
        List<CategoryEntity>categoryEntityList=categoryService.getAllCategory();
        model.addAttribute("productEntityList",productEntityList);
        model.addAttribute("categoryEntityList",categoryEntityList);
         String username=(String) session.getAttribute("username");
         if(username != null)
             model.addAttribute("username",username);
        return "index";
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "LoginForm";
    }

    @RequestMapping("/logout")
    public String  logout(HttpSession session){
        session.removeAttribute("userid");
        session.removeAttribute("username");
        return "redirect:";
    }

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping("/dashboard/categoryform")
    public String dashboardCategoryForm( Model model) {
        List<CategoryEntity> categoryEntityList=categoryService.getAllCategory();
        model.addAttribute("categoryEntityList",categoryEntityList);
        return "DashCategoryF";
    }

    @RequestMapping("/dashboard/categorysubmit")
    public String dashboardCategorySubmit(@RequestParam("category") String categoryname) {
        CategoryEntity entity=new CategoryEntity(categoryname);
        categoryService.saveCategory(entity);
        return "redirect:/dashboard/categoryform";

    }
    @RequestMapping("/dashboard/categorydelete/{cid}")
    public String dashboardCategoryDelete(@PathVariable String cid)
    {
        int id=Integer.parseInt(cid);
        categoryService.deleteCategory(id);
        return "redirect:/dashboard/categoryform";
    }

    @RequestMapping("/dashboard/productform")
    public String dashboardProductForm(Model model) {
         List<CategoryEntity> categoryEntityList=categoryService.getAllCategory();
         model.addAttribute("categoryEntityList",categoryEntityList);
        return "dashboardProductForm";
    }
    @RequestMapping("/dashboard/productsubmit")

    public String dashboardCategorySubmit(@RequestParam("name") String name,
                                          @RequestParam("price")String price,
                                          @RequestParam("category")String category_id,
                                          @RequestParam("details")String details,
                                          @RequestParam ("image") MultipartFile file )throws  Exception {
        String filename= file.getOriginalFilename();
        Path path = Paths.get(uploadpath+filename);
        Files.write(path,file.getBytes());
        int product_price=Integer.parseInt(price);
        CategoryEntity categoryEntity=categoryService.getCategoryById(Integer.parseInt(category_id));
        ProductEntity productEntity=new ProductEntity(name,product_price,details,filename,categoryEntity);
        productService.saveProduct(productEntity);


        return "redirect:/dashboard/productlist";
    }
    @RequestMapping("/dashboard/productlist")
    public  String dashboardProductList(Model model){
        List<ProductEntity> productEntityList=productService.getAllProduct();
        model.addAttribute("productEntityList",productEntityList);

      return "dashboardproductlist";

    }
    @RequestMapping("/productimages/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws Exception
    {
        Path path = Paths.get(uploadpath + filename);
        UrlResource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .body(resource);
    }
    @RequestMapping("/signupform")
    public String signupform(){
        return "signupform";
    }

    @RequestMapping("/signupsubmit")
    public String signupsubmit(@RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("mobile")String mobile,
                               @RequestParam("address") String address,
                               @RequestParam("name")MultipartFile photo,
                               @RequestParam("password")String password) throws Exception{
        String filename= photo.getOriginalFilename();
        Path path = Paths.get(useruploadpath+filename);
        Files.write(path,photo.getBytes());
        User user=new User(mobile, "{noop}"+password,name,email,address,filename);
        user.setRoles("ROLE_USER");
        userService.saveUser(user);
        return "redirect:/login";
    }

    @RequestMapping("/checkUserRole")
    @ResponseBody
    public String checkUserRole(Authentication auth, HttpSession session) {
        String Role = (String) auth.getAuthorities().toArray()[0].toString();
        if (Role.equals("ROLE_ADMIN"))
            return "redirect:/dashboard";
        else {
            User user = (User) auth.getPrincipal();
            session.setAttribute("userid", user.getId());
            session.setAttribute("username", user.getName());
            return "redirect:/";
        }
    }
}