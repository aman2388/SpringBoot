import java.util.*;

public class InvoiceData implements InvoiceDataInterface {

    /*
    All the code in this class is just dummy code to make the class compile.
    It is simulating talking to a database
     */

    public InvoiceData() {
        
    }

    @Override
    public List<Invoice> getAllInvoicesForCustomer(Integer customerId) {
        return new ArrayList<>();
    }

    @Override
    public List<Invoice> getAllOverdueInvoices() {
        return new ArrayList<>();
    }

    @Override
    public Invoice findInvoice(Integer invoiceId) {
        return new Invoice(1,1,12.34, null);
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        return true;
    }

}
