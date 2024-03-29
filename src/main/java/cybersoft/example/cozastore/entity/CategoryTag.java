package cybersoft.example.cozastore.entity;

import cybersoft.example.cozastore.entity.ids.CategoryTagIds;
import javax.persistence.*;

import java.util.Set;

@Entity(name = "category_tag")
public class CategoryTag {

    @EmbeddedId
    private CategoryTagIds ids;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false,updatable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false,updatable = false)
    private TagEntity tag;

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

    public CategoryTagIds getIds() {
        return ids;
    }

    public void setIds(CategoryTagIds ids) {
        this.ids = ids;
    }
}
