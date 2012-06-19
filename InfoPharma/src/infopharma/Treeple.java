/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma;

/**
 *
 * @author dfernandez
 */
public class Treeple {
    
    public final Object field;
    public Object title;
    public Object size;
    
    public Treeple(Object field, String title, Object size) {
        this.field = field;
        this.title = title;
        this.size = size;
    }
    
    public Treeple(Object field) {
        this.field = field;
    }
    
}
