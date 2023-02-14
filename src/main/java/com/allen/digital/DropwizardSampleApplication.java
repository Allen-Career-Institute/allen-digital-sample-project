package com.allen.digital;

import com.allen.digital.core.IllegalArgumentExceptionMapper;
import com.allen.digital.resources.SampleResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardSampleApplication extends Application<DropwizardSampleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardSampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardSample";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardSampleConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardSampleConfiguration configuration,
                    final Environment env) {
        env.jersey().register(new IllegalArgumentExceptionMapper(env.metrics()));
        env.jersey().register(new SampleResource());


    }

}
