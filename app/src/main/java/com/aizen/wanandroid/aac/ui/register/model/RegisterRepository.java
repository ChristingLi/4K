package com.aizen.wanandroid.aac.ui.register.model;

import com.aizen.wanandroid.aac.BaseRepository;

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
public class RegisterRepository extends BaseRepository {


}

@Component(modules = RegisterProvider.class)
interface RegisterComponent {

    void injectRegisterVM(RegisterViewModel model);
}

@Module
class RegisterProvider {
    @Provides
    public RegisterRepository getRegisterRepository(){
        return new RegisterRepository();
    }
}
