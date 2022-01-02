
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import org.primefaces.component.datatable.DataTable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a.abbas
 */
public class DataTableSystemEventListner implements SystemEventListener{
public void processEvent(SystemEvent event) throws AbortProcessingException {
   if(event.getSource() instanceof DataTable) {
        DataTable dataTable = (DataTable) event.getSource();
   if(dataTable.getFilters().size()==0){
      dataTable.resetValue();
         }
      }
   }

    @Override
    public boolean isListenerForSource(Object source) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

