package pl.switusia.itemlibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("")
    public List<Item> getAll(){
        return itemRepository.getAll();
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable("id") int id){
        return itemRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Item> items){
        itemRepository.save(items);
        return 0;
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Item updatedItem){
        Item item = itemRepository.getById(id);

        if(item != null){
            item.setName(updatedItem.getName());
            item.setPrice(updatedItem.getPrice());

            itemRepository.update(item);

            return 0;
        }else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Item updatedMovie){
        Item movie = itemRepository.getById(id);

        if(movie != null){
            if(updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
            if(updatedMovie.getPrice() > 0) movie.setPrice(updatedMovie.getPrice());

            itemRepository.update(movie);
            return 0;
        }else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return itemRepository.delete(id);
    }
}
