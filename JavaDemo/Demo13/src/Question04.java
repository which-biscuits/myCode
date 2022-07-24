import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 11412
 * 封装成List<Book>，并且输出封装好的集合
 * Book(String category, String title, String author, int year, double price)
 *
 * 将其封装成List<Book>对象返回，并且输出集合中的每个元素
 */
public class Question04 {
    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        InputStream inputStream = Question04.class.getResourceAsStream("./books.xml");
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Book> bookList = new ArrayList<>();
        for (Element book : root.elements("book")) {
            bookList.add(new Book(book.attributeValue("category"), book.element("title").getText(), book.element("author").getText(),
                    Integer.parseInt(book.element("year").getText()), Double.parseDouble(book.element("price").getText())));
        }
        bookList.forEach(System.out::println);
    }

}

class Book {
    private String category;
    private String title;
    private String author;
    private int year;
    private double price;

    public Book(String category, String title, String author, int year, double price) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public Book() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
