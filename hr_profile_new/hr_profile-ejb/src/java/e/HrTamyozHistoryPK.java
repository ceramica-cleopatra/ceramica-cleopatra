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
public class HrTamyozHistoryPK implements Serializable {
    private String ERROR;

    public HrTamyozHistoryPK() {
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrTamyozHistoryPK)) {
            return false;
        }
        HrTamyozHistoryPK other = (HrTamyozHistoryPK) object;
        return true;
    }

    @Override
    public String toString() {
        return (ERROR);
    }

}
