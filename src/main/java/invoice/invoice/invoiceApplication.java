package invoice.invoice;

import org.dalesbred.Database;

import invoice.invoice.resources.InvoiceResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;

public class invoiceApplication extends Application<invoiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new invoiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "invoice";
    }

    @Override
    public void initialize(final Bootstrap<invoiceConfiguration> bootstrap) {
    }

    @Override
    public void run(final invoiceConfiguration configuration,
                    final Environment environment) {
    	DataSourceFactory config = configuration.getDataSourceFactory();
		final Database database = Database.forUrlAndCredentials(config.getUrl(), config.getUser(),
				config.getPassword());
		
		environment.jersey().register(new InvoiceResource(database));

    }
    

}
