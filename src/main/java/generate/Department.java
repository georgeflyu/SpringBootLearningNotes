package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * department
 * @author 
 */
@Data
public class Department implements Serializable {
    private Integer id;

    private String name;

    private Integer parentId;

    private static final long serialVersionUID = 1L;
}