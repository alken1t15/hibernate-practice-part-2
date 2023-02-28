
import Entity.Option;
import Entity.Category;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String category = null;
        String option;
        String[] arr;
        Category categorie;
        try {
            System.out.print("Введите название категории: ");
            category = bufferedReader.readLine();
            TypedQuery<Category> categoryTypedQuery = manager.createQuery("select c from Category  c", Category.class);
            List<Category> categories = categoryTypedQuery.getResultList();
            for (Category category1 : categories) {
                if (category1.getName().equals(category)) {
                    throw new RuntimeException();
                }
            }
            categorie = new Category(category);
            manager.getTransaction().begin();
            manager.persist(categorie);
            manager.getTransaction().commit();
            System.out.print("Введите название характеристик (через запятую): ");
            option = bufferedReader.readLine();
            arr = option.split(",");
            for (String s : arr) {
                Option option1 = new Option();
                option1.setCategory(categorie);
                option1.setName(s.trim());
                manager.getTransaction().begin();
                manager.persist(option1);
                manager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("Категория с названием '" + category + "' уже существует");
        }
    }
}