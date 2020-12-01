package formalab.gestion.produits.controllers;

import formalab.gestion.produits.entities.Category;
import formalab.gestion.produits.entities.Product;
import formalab.gestion.produits.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = {"","/"})
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoriesList = categoryService.findAll();
        return new ResponseEntity<>(categoriesList, HttpStatus.OK);
    }

    //amel like product
    /*
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }
    */

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}/products")
    public List<Product> getCategoryProducts(@PathVariable Long id) {
        List<Product> products = categoryService.findById(id).getProducts();
        return products;
    }

    //amel maj like product
   /* @PostMapping(value = {"","/"})
    public Category createNewCategory(@RequestBody Category category) {
        categoryService.save(category);
        return category;
    }*/

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Void> createNewCategory(@Valid @RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //AMEL MAJ LIKE PRODUCT
    /*@PutMapping(value = {"","/"})
    public Category updateCategory(@RequestBody Category category) {
        categoryService.update(category);
        return category;
    }*/

    @PutMapping(value = {"/{id}"}) //marche //à voir d'autres possibilités
    public ResponseEntity<Void> updateCategory(@PathVariable Long id,
                                               @Valid @RequestBody Category category) {
        //
        Category category1 = categoryService.findById(id);
        category1.setName(category.getName());
        //
        categoryService.update(category1);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //amel
   /* @DeleteMapping("/{id}")
    public void deleteCategory(Long id) {
        categoryService.delete(id);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //test amel : ajout de /name
    /*@GetMapping("/name/{name}")
    public void findByName(@PathVariable  String name) {
        categoryService.findByName(name);
    }*/
    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        Category category = categoryService.findByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
