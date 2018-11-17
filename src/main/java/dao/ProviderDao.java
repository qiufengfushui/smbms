package dao;

import entity.Provider;
import org.apache.ibatis.annotations.Param;
import util.PageBean;

import java.util.List;

public interface ProviderDao  {

    List<Provider> getAllProvider();

    int addProvider(Provider provider);

    int findByProNameCount(@Param("proName") String proName);

    int findByProCodeCount(@Param("proCode") String proCode);

    int findByProCodeAndProNameCount(@Param("proCode") String proCode, @Param("proName") String proName);

    int getAllProviderCount();

    List<Provider> findByProvider(@Param("pageBean") PageBean<Provider> pageBean, @Param("proCode") String proCode, @Param("proName") String proName);

    Provider getProvider(@Param("id") long id);

    int findByProIdCount(@Param("id") long id);

    int delProvider(@Param("id") long id);

    int updateProviderInfo(@Param("provider") Provider provider, @Param("modifyBy") long modifyBy);

    int getProviderCount(@Param("proName") String proName, @Param("proCode") String proCode);
}
