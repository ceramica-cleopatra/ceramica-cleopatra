import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.faces.event.SystemEvent;
import javax.servlet.http.HttpServletResponse;

public class CustomExceptionHandler extends ExceptionHandler {

    private ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }


    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        for (ExceptionQueuedEvent event : getUnhandledExceptionQueuedEvents()) {
            Throwable throwable = ((ExceptionQueuedEventContext) event.getSource()).getException();

            // Log the exception or handle it accordingly
            logException(throwable);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
                facesContext.responseComplete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        wrapped.handle();
    }

    private void logException(Throwable throwable) {
        // Implement your logging logic here
        System.err.println("Exception+++++++++++++ " + throwable.getMessage());
    }

    @Override
    public ExceptionQueuedEvent getHandledExceptionQueuedEvent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<ExceptionQueuedEvent> getUnhandledExceptionQueuedEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<ExceptionQueuedEvent> getHandledExceptionQueuedEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processEvent(SystemEvent exceptionQueuedEvent) throws AbortProcessingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isListenerForSource(Object source) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Throwable getRootCause(Throwable t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}