import java.util.List;

public interface InvoiceUtilitiesInterface {
    void setInvoiceData(InvoiceDataInterface invoiceData);

    Invoice generateInvoice();

    void payInvoice(Integer invoiceId);

    List<Invoice> getInvoicesForCustomer(Integer customerId);
}
