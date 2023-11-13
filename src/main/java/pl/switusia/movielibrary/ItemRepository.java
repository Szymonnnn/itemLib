package pl.switusia.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Item> getAll(){
        List<Item> query = jdbcTemplate.query("SELECT id, name, price FROM item",
                BeanPropertyRowMapper.newInstance(Item.class));

        return query;
    }

    public Item getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, name, price FROM item WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(Item.class), id);
    }

    public int save(List<Item> movies) {
        movies.forEach(movie -> jdbcTemplate.update("INSERT INTO item(name, price) VALUES(?, ?)",
                movie.getName(), movie.getPrice()));

        return 0;
    }

    public int update(Item movie){
        return jdbcTemplate.update("UPDATE item SET name = ?, price = ? WHERE id = ?",
                movie.getName(), movie.getPrice(), movie.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM item WHERE id=?", id);
    }
}
