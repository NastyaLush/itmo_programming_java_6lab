package test.laba.server.mycommands;

import test.laba.common.IO.Colors;

import test.laba.common.dataClasses.Product;
import test.laba.common.dataClasses.UnitOfMeasure;
import test.laba.common.exception.AlreadyHaveTheseProduct;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * the collection class, contains fields: collection and date of creation
 */
@XmlRootElement(name = "root")
public class Root {
    @XmlTransient
    private ZonedDateTime dateOfCreation;
    private HashMap<Long, Product> products = new HashMap<>();

    /**
     * the constructor, create only date creation
     */
    public Root() {
        this.dateOfCreation = ZonedDateTime.now();
    }

    /**
     * the constructor, add collection
     *
     * @param products argument for adding
     */
    public Root(HashMap<Long, Product> products) {
        this.products = products;
    }

    //don't delete, using for parsing
    public void setProducts(HashMap<Long, Product> products) {
        this.products = products;
    }

    public HashMap<Long, Product> getProducts() {
        return products;
    }

    public ZonedDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * @param key key for searching
     * @return product wish key equal to argument
     */
    public Product getProductByKey(Long key) {
        return products.get(key);
    }

    /**
     * @param id id for searching
     * @return key of product by id
     */
    public Long getKeyOnID(Long id) {
        AtomicReference<Long> answer = new AtomicReference<>();
        products.entrySet().stream().filter(x -> x.getValue().getId() == id).limit(1).forEach(e -> answer.set(e.getKey()));
        return answer.get();
    }

    public void setProduct(Product product) {
        products.put(createKey(), product);
        dateOfCreation = ZonedDateTime.now();
    }
    public long createID() {
        long rightID = 0;
        final int constant = 9;
        final int numberWithDegree = 10;
        final int maxDegreeNumberOfLong = 18;
        boolean flag = true;
        while (flag) {
            rightID = (long) (Math.random() * constant * Math.pow(numberWithDegree, maxDegreeNumberOfLong));
            if (!products.containsKey(rightID)) {
                flag = false;
            }
        }
        return rightID;
    }

    public void setProductWithKey(Long key, Product product) throws AlreadyHaveTheseProduct {
        if (!products.containsKey(key)) {
            products.put(key, product);
            dateOfCreation = ZonedDateTime.now();
        } else {
            throw new AlreadyHaveTheseProduct("This key is already exists");
        }
    }

    public void updateProductWithKey(Long key, Product product) {
        products.put(key, product);
        dateOfCreation = ZonedDateTime.now();
    }

    /**
     * @param id id for searching
     * @return true if product of collection contains this id and false in another case
     */
    public boolean containsID(Long id) {
        return products.values().stream().anyMatch(e -> e.getId() == id);
    }

    private Long createKey() {
        long key = 0;
        while (getProducts().containsKey(key)) {
            key++;
        }
        return key;
    }

    /**
     * the method which clear collection
     */
    public void clear() {
        products.clear();
    }

    /**
     * create the string with information about collection
     *
     * @return string with information about collection
     */
    public String infoAboutCollection() {
        return "Class of collection: " + getProducts().getClass()
                + "\nDate of initialization: " + getDateOfCreation()
                + "\nNumber of elements: " + getProducts().size();
    }

    /**
     * remove one product if argument matches with UnitOfMeasure field of product in collection
     *
     * @param unitOfMeasure argument for removing
     */
    public void removeAnyByUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        products.entrySet()
                .stream()
                .filter(x -> x.getValue().getUnitOfMeasure() == unitOfMeasure)
                .findFirst().map(e -> products.remove(e.getKey()));
    }

    /**
     * remove all products which less than argument
     *
     * @param product argument for comparing
     */
    public void removeIfLess(Product product) {
        products.entrySet().removeIf(entry -> product.compareTo(entry.getValue()) > 0);
    }

    /**
     * remove all products which key less than argument
     *
     * @param key key for comparing
     */
    public void removeIfKeyLess(Long key) {
        products.entrySet().removeIf(entry -> key > entry.getKey());
    }

    /**
     * delete product by key
     *
     * @param deleteKey key for deleting
     */
    public void remove(Long deleteKey) {
        products.remove(deleteKey);
    }

    /**
     * create string with collection value
     *
     * @return string
     */
    public String showCollection() {
        StringBuilder s = new StringBuilder(Colors.BlUE + "Products: \n" + Colors.END);
        products.forEach((key, value) -> s.append("Ключ: ").append(key).append(" ").append(value).append("\n"));
        return s.toString();
    }

    /**
     * @return number average Of Manufacture Cost of products in collection
     */
    public double sumOfManufactureCost() {
        double answer = 0.0;
        answer += products.values().stream().mapToDouble(Product::getManufactureCost).sum();
        return answer;
    }

    /**
     * @return hashmap where keys- field value price in product and values- count of these products with this price
     */
    public Map<Long, Long> groupCountingByPrice() {
        return products.values()
                .stream().map(Product::getPrice).distinct().collect(Collectors.toMap(e -> e, e -> products.values()
                        .stream().map(Product::getPrice).filter(e::equals).count()));

    }


    @Override
    public String toString() {
        return "Root{"
                + "products=" + products
                + ", dateOfLastModification=" + dateOfCreation + '}';
    }


}
