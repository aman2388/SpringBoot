import java.util.List;

public interface InvoiceDataInterface {
    List<Invoice> getAllInvoicesForCustomer(Integer customerId);

    List<Invoice> getAllOverdueInvoices();

    Invoice findInvoice(Integer invoiceId);

    boolean updateInvoice(Invoice invoice);
}
