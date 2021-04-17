package me.itslucas.bookstore.controller;

import me.itslucas.bookstore.domain.*;
import me.itslucas.bookstore.domain.security.Role;
import me.itslucas.bookstore.domain.security.UserRole;
import me.itslucas.bookstore.service.*;
import me.itslucas.bookstore.service.impl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.logging.Logger;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private UserShippingService userShippingService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Book> books = bookService.findAll();
        int start = books.size()-1;
        ArrayList<Book> dispBooks = new ArrayList<>();
        for(int i = start; i >=0 && i>= start-3; i--) {
            dispBooks.add(books.get(i));
        }
        model.addAttribute("books",dispBooks);
        return "index";
    }

    @GetMapping("logout")
    public String logout(Model model) {
        return "login";
    }
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ModelAndView authenticate(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @ModelAttribute("login") Login login,
                                     Model model){
        ModelAndView mav = null;
        Logger.getLogger("test").info("111");
        User user = userService.findByUsername(login.getUsername());
        if(user==null) {
            mav=new ModelAndView("error");
        }
        else if (user.getPassword().equals(login.getPassword())) {
            mav = new ModelAndView("index");
            mav.addObject("name", user.getUsername());
        }

        return mav;
    }

    @PostMapping("/registercheck")
    public ModelAndView reg(@RequestParam(name="username", required=false, defaultValue="World") String name,
                               @RequestParam(name="password", required=false, defaultValue="World") String pass,
                               @RequestParam(name="email", required=false, defaultValue="World") String email,
                               @RequestParam(name="phone", required=false, defaultValue="World") String phone,
                               Model model) throws Exception {
        ModelAndView mav = null;
        UserDetails ud = userSecurityService.loadUserByUsername(name);
        if(null == ud) {
            User user = new User();
            user.setUsername(name);
            user.setPassword(pass);
            user.setEmail(email);
            user.setPhone(phone);
            Role role = new Role();
            role.setRoleId(1);
            role.setName("ROLE_USER");
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, role));
            userService.createUser(user, userRoles);

            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            mav = new ModelAndView("login");
        } else mav = new ModelAndView("error");
        return mav;
    }

    @GetMapping("/product")
    public String product(Model model) {
        model.addAttribute("books",bookService.findAll());
        return "product";
    }

    @GetMapping("/productdetail")
    public String producttest(Model model,@RequestParam(name="id")Long id) {
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "productdetail";
    }

    @GetMapping("/viptest")
    public String viptest(Model model) {
        return "vip";
    }


}
