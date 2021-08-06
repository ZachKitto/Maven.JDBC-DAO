package daos;

import java.util.List;

public interface DAOInterface<SomeEntity> {

    public SomeEntity findById(int id);

    public List findAll();

    public SomeEntity update(int id, SomeEntity dto);

    public SomeEntity create(SomeEntity dto);

    public void delete(int id);
}
