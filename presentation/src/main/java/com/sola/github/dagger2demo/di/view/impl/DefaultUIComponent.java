package com.sola.github.dagger2demo.di.view.impl;

import com.sola.github.dagger2demo.di.scope.BindingType;
import com.sola.github.dagger2demo.di.view.IBindingAdapter;
import com.sola.github.dagger2demo.di.view.adapter.DefaultBindingAdapter;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Sola
 * 2017/3/24.
 */
@BindingType
@Component(modules = DefaultUIComponent.DefaultUIModule.class)
public interface DefaultUIComponent extends android.databinding.DataBindingComponent {


    @Module
    class DefaultUIModule {

        @Provides
        @BindingType
        IBindingAdapter provideBindingAdapter() {
            return new DefaultBindingAdapter();
        }
    }

}
