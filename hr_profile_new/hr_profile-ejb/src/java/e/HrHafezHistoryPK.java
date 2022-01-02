/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package e;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author ahmed abbas
 */
@Embeddable
public class HrHafezHistoryPK implements Serializable {
    private String ERROR;

    public HrHafezHistoryPK() {
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrHafezHistoryPK)) {
            return false;
        }
        HrHafezHistoryPK other = (HrHafezHistoryPK) object;
        return true;
    }

    @Override
    public String toString() {
        return (ERROR);
    }

}
