package serviceImpl;

import dao.RoleDao;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.RoleService;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;

    @Override
    public List<Role> findByRoleList() {
        return roleDao.findByRoleList();
    }
}
