package me.itslucas.bookstore.controller;

import me.itslucas.bookstore.domain.Book;
import me.itslucas.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitBookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/addallbook")
    public boolean addAllBook() {
        Book book1 = new Book();
        book1.setTitle("美食与文明");
        book1.setDescription("一本既适合吃货，也适合历史爱好者的世界史读物");
        book1.setInStockNumber(100);
        book1.setActive(true);
        book1.setLanguage("中文");
        book1.setNumberOfPages(255);
        book1.setListPrice(57.3);
        book1.setAuthor("123");
        book1.setCategory("main");
        book1.setFormat("format");
        book1.setPublisher("publisher");
        book1.setPublicationDate("2020.1.1");
        bookService.save(book1);
        book1 = new Book();
        book1.setTitle("此时此地：玛格南街头摄影经典");
        book1.setDescription("跟随布列松、厄威特、巴贝、马克·吕布等66位大师的脚步，一起领略摄影的魔力吧。");
        book1.setInStockNumber(100);
        book1.setActive(true);
        book1.setLanguage("中文");
        book1.setNumberOfPages(135);
        book1.setListPrice(77.3);
        book1.setAuthor("123");
        book1.setCategory("main");
        book1.setFormat("format");
        book1.setPublisher("publisher");
        book1.setPublicationDate("2020.1.1");
        bookService.save(book1);
        book1 = new Book();
        book1.setTitle("月亮与六便士");
        book1.setDescription("不再徘徊、不再犹豫，为梦想拼搏，去追求自己内心真正想要的东西");
        book1.setInStockNumber(200);
        book1.setActive(true);
        book1.setLanguage("中文");
        book1.setNumberOfPages(655);
        book1.setListPrice(127.6);
        book1.setAuthor("123");
        book1.setCategory("main");
        book1.setFormat("format");
        book1.setPublisher("publisher");
        book1.setPublicationDate("2020.1.1");
        bookService.save(book1);
        book1 = new Book();
        book1.setTitle("图解日本歌舞伎");
        book1.setDescription("本书将带领大家从零开始了解歌舞伎，从基础知识到趣闻逸话，从经典剧目到精彩舞台表演，全面解读与歌舞伎相关的各种知识");
        book1.setInStockNumber(332);
        book1.setActive(true);
        book1.setLanguage("中文");
        book1.setNumberOfPages(176);
        book1.setListPrice(47.9);
        book1.setAuthor("123");
        book1.setCategory("main");
        book1.setFormat("format");
        book1.setPublisher("publisher");
        book1.setPublicationDate("2020.1.1");
        bookService.save(book1);
        book1 = new Book();
        book1.setTitle("非洲通史");
        book1.setDescription("黑人抬棺、超级英雄电影、种族冲突……关于非洲的形象我们并不陌生，但我们对其整体性的历史却似乎知之甚少");
        book1.setInStockNumber(200);
        book1.setActive(true);
        book1.setLanguage("中文");
        book1.setNumberOfPages(186);
        book1.setListPrice(39.2);
        book1.setAuthor("123");
        book1.setCategory("main");
        book1.setFormat("format");
        book1.setPublisher("publisher");
        book1.setPublicationDate("2020.1.1");
        bookService.save(book1);
        book1 = new Book();
        book1.setTitle("骨骼之美");
        book1.setDescription("为了适应千变万化的环境，脊椎动物演化成各种各样令人惊叹的形态，骨骼在具有支撑、保护、运动等功能的同时，更隐藏了动物的系统发育、行为乃至生存环境的秘密");
        book1.setInStockNumber(265);
        book1.setActive(true);
        book1.setLanguage("中文");
        book1.setNumberOfPages(117);
        book1.setListPrice(59.2);
        book1.setAuthor("123");
        book1.setCategory("main");
        book1.setFormat("format");
        book1.setPublisher("publisher");
        book1.setPublicationDate("2020.1.1");
        bookService.save(book1);

        return true;
    }
}
