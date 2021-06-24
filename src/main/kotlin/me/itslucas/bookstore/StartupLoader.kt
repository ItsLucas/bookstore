package me.itslucas.bookstore

import me.itslucas.bookstore.domain.Book
import me.itslucas.bookstore.domain.User
import me.itslucas.bookstore.repository.BookRepository
import me.itslucas.bookstore.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class StartupLoader : ApplicationRunner {
    @Autowired
    private val bookService: BookRepository? = null

    @Autowired
    private val userRepository: UserRepository? = null

    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        if (bookService!!.count() == 0L) {
            var book1 = Book()
            book1.title = "美食与文明"
            book1.description = "一本既适合吃货，也适合历史爱好者的世界史读物"
            book1.inStockNumber = 100
            book1.active = true
            book1.language = "中文"
            book1.numberOfPages = 255
            book1.listPrice = 57.3
            book1.author = "123"
            book1.category = "main"
            book1.format = "format"
            book1.publisher = "publisher"
            book1.publicationDate = "2020.1.1"
            bookService.save(book1)
            book1 = Book()
            book1.title = "此时此地：玛格南街头摄影经典"
            book1.description = "跟随布列松、厄威特、巴贝、马克·吕布等66位大师的脚步，一起领略摄影的魔力吧。"
            book1.inStockNumber = 100
            book1.active = true
            book1.language = "中文"
            book1.numberOfPages = 135
            book1.listPrice = 77.3
            book1.author = "123"
            book1.category = "main"
            book1.format = "format"
            book1.publisher = "publisher"
            book1.publicationDate = "2020.1.1"
            bookService.save(book1)
            book1 = Book()
            book1.title = "月亮与六便士"
            book1.description = "不再徘徊、不再犹豫，为梦想拼搏，去追求自己内心真正想要的东西"
            book1.inStockNumber = 200
            book1.active = true
            book1.language = "中文"
            book1.numberOfPages = 655
            book1.listPrice = 127.6
            book1.author = "123"
            book1.category = "main"
            book1.format = "format"
            book1.publisher = "publisher"
            book1.publicationDate = "2020.1.1"
            bookService.save(book1)
            book1 = Book()
            book1.title = "图解日本歌舞伎"
            book1.description = "本书将带领大家从零开始了解歌舞伎，从基础知识到趣闻逸话，从经典剧目到精彩舞台表演，全面解读与歌舞伎相关的各种知识"
            book1.inStockNumber = 332
            book1.active = true
            book1.language = "中文"
            book1.numberOfPages = 176
            book1.listPrice = 47.9
            book1.author = "123"
            book1.category = "main"
            book1.format = "format"
            book1.publisher = "publisher"
            book1.publicationDate = "2020.1.1"
            bookService.save(book1)
            book1 = Book()
            book1.title = "非洲通史"
            book1.description = "黑人抬棺、超级英雄电影、种族冲突……关于非洲的形象我们并不陌生，但我们对其整体性的历史却似乎知之甚少"
            book1.inStockNumber = 200
            book1.active = true
            book1.language = "中文"
            book1.numberOfPages = 186
            book1.listPrice = 39.2
            book1.author = "123"
            book1.category = "main"
            book1.format = "format"
            book1.publisher = "publisher"
            book1.publicationDate = "2020.1.1"
            bookService.save(book1)
            book1 = Book()
            book1.title = "骨骼之美"
            book1.description = "为了适应千变万化的环境，脊椎动物演化成各种各样令人惊叹的形态，骨骼在具有支撑、保护、运动等功能的同时，更隐藏了动物的系统发育、行为乃至生存环境的秘密"
            book1.inStockNumber = 265
            book1.active = true
            book1.language = "中文"
            book1.numberOfPages = 117
            book1.listPrice = 59.2
            book1.author = "123"
            book1.category = "main"
            book1.format = "format"
            book1.publisher = "publisher"
            book1.publicationDate = "2020.1.1"
            bookService.save(book1)

            val user = User()
            user.setUsername("test")
            user.setPassword("test")
            user.email = "mail@mail.com"
            user.phone = "123456"
            userRepository!!.save(user)
        }
    }
}