package me.itslucas.bookstore;

import me.itslucas.bookstore.domain.Book;
import me.itslucas.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupLoader implements ApplicationRunner {
    @Autowired
    private BookRepository bookService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (bookService.count() == 0) {
            Book book1 = Book.builder().title("美食与文明")
                    .description("一本既适合吃货，也适合历史爱好者的世界史读物")
                    .inStockNumber(100)
                    .active(true)
                    .language("中文")
                    .numberOfPages(255)
                    .listPrice(57.3)
                    .author("123")
                    .category("main")
                    .format("format")
                    .publisher("publisher")
                    .publicationDate("2020.1.1")
                    .build();
            bookService.save(book1);
            book1 = Book.builder()
                    .title("此时此地：玛格南街头摄影经典")
                    .description("跟随布列松、厄威特、巴贝、马克·吕布等66位大师的脚步，一起领略摄影的魔力吧。")
                    .inStockNumber(100)
                    .active(true)
                    .language("中文")
                    .numberOfPages(135)
                    .listPrice(77.3)
                    .author("123")
                    .category("main")
                    .format("format")
                    .publisher("publisher")
                    .publicationDate("2020.1.1")
                    .build();
            bookService.save(book1);
            book1 = Book.builder()
                    .title("月亮与六便士")
                    .description("不再徘徊、不再犹豫，为梦想拼搏，去追求自己内心真正想要的东西")
                    .inStockNumber(200)
                    .active(true)
                    .language("中文")
                    .numberOfPages(655)
                    .listPrice(127.6)
                    .author("123")
                    .category("main")
                    .format("format")
                    .publisher("publisher")
                    .publicationDate("2020.1.1")
                    .build();
            bookService.save(book1);
            book1 = Book.builder()
                    .title("图解日本歌舞伎")
                    .description("本书将带领大家从零开始了解歌舞伎，从基础知识到趣闻逸话，从经典剧目到精彩舞台表演，全面解读与歌舞伎相关的各种知识")
                    .inStockNumber(332)
                    .active(true)
                    .language("中文")
                    .numberOfPages(176)
                    .listPrice(47.9)
                    .author("123")
                    .category("main")
                    .format("format")
                    .publisher("publisher")
                    .publicationDate("2020.1.1")
                    .build();
            bookService.save(book1);
            book1 = Book.builder()
                    .title("非洲通史")
                    .description("黑人抬棺、超级英雄电影、种族冲突……关于非洲的形象我们并不陌生，但我们对其整体性的历史却似乎知之甚少")
                    .inStockNumber(200)
                    .active(true)
                    .language("中文")
                    .numberOfPages(186)
                    .listPrice(39.2)
                    .author("123")
                    .category("main")
                    .format("format")
                    .publisher("publisher")
                    .publicationDate("2020.1.1")
                    .build();
            bookService.save(book1);
            book1 = Book.builder()
                    .title("骨骼之美")
                    .description("为了适应千变万化的环境，脊椎动物演化成各种各样令人惊叹的形态，骨骼在具有支撑、保护、运动等功能的同时，更隐藏了动物的系统发育、行为乃至生存环境的秘密")
                    .inStockNumber(265)
                    .active(true)
                    .language("中文")
                    .numberOfPages(117)
                    .listPrice(59.2)
                    .author("123")
                    .category("main")
                    .format("format")
                    .publisher("publisher")
                    .publicationDate("2020.1.1")
                    .build();
            bookService.save(book1);
        }
    }
}
