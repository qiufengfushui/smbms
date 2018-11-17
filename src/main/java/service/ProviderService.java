package service;

import entity.Provider;
import util.PageBean;

import java.util.List;

public interface ProviderService {
    List<Provider> getAllProvider();

    int addProvider(Provider provider);

    int findByProNameCount(String proName);

    int findByProCodeCount(String proCode);

    int findByProCodeAndProNameCount(String proCode, String proName);

    int getAllProviderCount();

    PageBean<Provider> findByProvider(PageBean<Provider> pageBean, String proName, String proCode);

    Provider getProvider(long id);

    String delProvider(long id);

    int updateProviderInfo(Provider provider, long modifyBy);

    int getProviderCount(String proName, String proCode);
}
