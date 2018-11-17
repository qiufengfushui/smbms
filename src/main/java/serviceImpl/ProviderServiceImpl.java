package serviceImpl;

import dao.BillDao;
import dao.ProviderDao;
import entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ProviderService;
import util.PageBean;

import java.util.List;

@Transactional
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    @Qualifier("providerDao")
    private ProviderDao providerDao;

    @Autowired
    @Qualifier("billDao")
    private BillDao billDao;

    @Override
    public List<Provider> getAllProvider() {
        return providerDao.getAllProvider();
    }

    @Override
    public int addProvider(Provider provider) {
        return providerDao.addProvider(provider);
    }

    @Override
    public int findByProNameCount(String proName) {
        return providerDao.findByProNameCount(proName);
    }

    @Override
    public int findByProCodeCount(String proCode) {
        return providerDao.findByProCodeCount(proCode);
    }

    @Override
    public int findByProCodeAndProNameCount(String proCode, String proName) {
        return providerDao.findByProCodeAndProNameCount(proCode,proName);
    }

    @Override
    public int getAllProviderCount() {
        return providerDao.getAllProviderCount();
    }

    @Override
    public PageBean<Provider> findByProvider(PageBean<Provider> pageBean, String proName, String proCode) {
        pageBean.setList(providerDao.findByProvider(pageBean,proCode,proName));
        return pageBean;
    }

    @Override
    public Provider getProvider(long id) {
        return providerDao.getProvider(id);
    }

    @Override
    public String delProvider(long id) {
        int num = 0;
        num = providerDao.findByProIdCount(id);
        if(num == 0){
            //不存在
            return "notexist";
        }
        num = billDao.findByBillCount(id);
        if(num > 0){
            return num+"";
        }
        num = providerDao.delProvider(id);
        if(num == 0){
            //删除失败
            return "false";
        }
        //删除成功
        return "true";
    }

    @Override
    public int updateProviderInfo(Provider provider, long modifyBy) {
        return providerDao.updateProviderInfo(provider,modifyBy);
    }

    @Override
    public int getProviderCount(String proName, String proCode) {
        return providerDao.getProviderCount(proName,proCode);
    }

}
