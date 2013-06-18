package denominator.dynect;

import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;

import denominator.Provider;
import denominator.dynect.InvalidatableTokenSupplier.Session;
import feign.Request;
import feign.RequestTemplate;
import feign.Target;

class SessionTarget implements Target<Session> {

    private final Provider provider;

    @Inject
    SessionTarget(Provider provider) {
        this.provider = provider;
    }

    @Override
    public Class<Session> type() {
        return Session.class;
    }

    @Override
    public String name() {
        return provider.name();
    }

    @Override
    public String url() {
        return provider.url();
    }

    @Override
    public Request apply(RequestTemplate input) {
        input.header("API-Version", "3.5.0");
        input.header(CONTENT_TYPE, APPLICATION_JSON);
        input.insert(0, url());
        return input.request();
    }
};
