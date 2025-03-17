package com.shivaprasad.lld.solid;


// Without - SRP
class Invoice {
    public void calculateTotal() {
        // Logic to calculate total.
    }

    public void printInvoice() {
        // Logic to format and print the invoice.
    }

    public void saveInvoiceToDB() {
        // Logic to save invoice to database.
    }
}

public class SRP {
    // Just main method code
}

// with SRP
class InvoiceCalculator {
    public void calculateTotal() {
        // Logic to calculate total.
    }
}

class InvoicePrinter {
    public void printInvoice(Invoice invoice) {
        // Logic to format and print the invoice.
    }
}

class InvoicePersistence {
    public void saveInvoiceToDB(Invoice invoice) {
        // Logic to save invoice to database.
    }
}