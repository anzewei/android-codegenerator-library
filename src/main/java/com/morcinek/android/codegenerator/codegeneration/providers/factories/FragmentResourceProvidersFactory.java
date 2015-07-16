package com.morcinek.android.codegenerator.codegeneration.providers.factories;

import com.google.common.collect.Lists;
import com.morcinek.android.codegenerator.codegeneration.providers.ResourceProvider;
import com.morcinek.android.codegenerator.codegeneration.providers.ResourceProvidersFactory;
import com.morcinek.android.codegenerator.codegeneration.providers.resources.AbstractResourceProvider;
import com.morcinek.android.codegenerator.codegeneration.providers.resources.ButtonProvider;
import com.morcinek.android.codegenerator.codegeneration.providers.resources.DefaultProvider;
import com.morcinek.android.codegenerator.codegeneration.providers.resources.GetterProvider;
import com.morcinek.android.codegenerator.extractor.model.Resource;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class FragmentResourceProvidersFactory implements ResourceProvidersFactory {

    @Override
    public ResourceProvider createResourceProvider(Resource resource) {
        if (isApplicable(resource, "Button", "ImageButton")) {
            return getResourceProviderWithContainerPrefix(new ButtonProvider(resource), "view.");
        } else if (isApplicable(resource, "EditText")) {
            return getResourceProviderWithContainerPrefix(new GetterProvider(resource), "view.");
        }
        return getResourceProviderWithContainerPrefix(new DefaultProvider(resource), "view.");
    }

    private AbstractResourceProvider getResourceProviderWithContainerPrefix(AbstractResourceProvider resourceProvider, String value) {
        resourceProvider.putExtra("CONTAINER_PREFIX", value);
        return resourceProvider;
    }

    private boolean isApplicable(Resource resource, String... resourcesNames) {
        return Lists.newArrayList(resourcesNames).contains(resource.getResourceType().getFullName());
    }
}
