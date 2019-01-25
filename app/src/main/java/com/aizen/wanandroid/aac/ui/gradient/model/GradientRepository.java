package com.aizen.wanandroid.aac.ui.gradient.model;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ld on 2019/1/2.
 *
 * @author ld
 * @date 2019/1/2
 * 描    述：
 */
public class GradientRepository {

}

@Module
class GradientProvider {
    @Provides
    public GradientRepository getProvider() {
        return new GradientRepository();
    }
}

@Component(modules = GradientProvider.class)
interface GradientComponent {

    void injectGradientViewModel(GradientViewModel model);
}
