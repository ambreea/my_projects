package main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class DbEmployee implements Operations {
    private static Session session;
    private static String hql;
    private static Query query;
    private static boolean aBoolean;
    private static int employeeId, departmentId;
    Transaction transaction = null;
    List<Department> departmentList;
    List<Object[]> employeeObject;
    List employeeOb;


    @Override
    public void connectDb() {
        try {
            session = HibernateUtil.createSessionFactory().openSession();

            if (session.isConnected())
                JOptionPane.showMessageDialog(null, "Connection with the database created successfuly.");
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
    }

    public static boolean verifyConnexion() {
        aBoolean = false;
        if (session == null) {
            aBoolean = true;
        }
        return aBoolean;
    }

    @Override
    public int insertEmployee(Employee employee) {
        if (session.isConnected()) {
            try {

                employeeId = (Integer) session.save(employee);
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return employeeId;
    }

    @Override
    public int deleteEmployee(int id) {
        if (session.isConnected()) {
            try {
                transaction = session.beginTransaction();
                hql = "delete from Employee e where e.id =:id";
                query = session.createQuery(hql);
                query.setParameter("id", id);
                employeeId = query.executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return employeeId;
    }

    @Override
    public boolean modifyEmployee(Employee newData) {
        aBoolean = false;
        if (session.isConnected()) {
            try {
                transaction = session.beginTransaction();
                Employee oldData = (Employee) session.get(Employee.class, newData.getId());

                if (newData.getName() != null)
                    oldData.setName(newData.getName());
                if (newData.getAge() != 0)
                    oldData.setAge(newData.getAge());
                if (newData.getAddress() != null)
                    oldData.setAddress(newData.getAddress());
                if (newData.getSalary() != 0)
                    oldData.setSalary(newData.getSalary());
                if (newData.getDepartment() != null)
                    oldData.setDepartment(newData.getDepartment());
                session.update(oldData);
                aBoolean = true;
                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return aBoolean;
    }

    @Override
    public List displayAll() {
        if (session.isConnected()) {
            try {
                transaction = session.beginTransaction();
                hql = "select e.id, e.name, e.age, e.address, e.salary, d.name from Employee e left join e.department d";
                query = session.createQuery(hql);
                query.setCacheable(true);
                employeeObject = query.list();
                transaction.commit();


            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return employeeObject;
    }

    @Override
    public List dispaySelectiveMultiple(String criteria) {
        if (session.isConnected()) {
            try {
                transaction = session.beginTransaction();
                query = session.createQuery(criteria);
                employeeObject = query.list();
                transaction.commit();

            } catch (HibernateException | IllegalStateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return employeeObject;
    }

    @Override
    public List dispaySelectiveSingle(String criteria) {
        if (session.isConnected()) {
            try {
                transaction = session.beginTransaction();
                query = session.createQuery(criteria);
                employeeOb = query.list();
                transaction.commit();

            } catch (HibernateException | IllegalStateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return employeeOb;
    }

    @Override
    public int insertDepartment(Department department) {
        if (session.isConnected()) {
            try {

                departmentId = (Integer) session.save(department);
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return departmentId;
    }

    @Override
    public int deleteDepartment(int departmentId) {
        if (session.isConnected()) {
            try {
                transaction = session.beginTransaction();
                hql = "delete from Department d where d.departmentId =:departmentId";
                query = session.createQuery(hql);
                query.setParameter("departmentId", departmentId);
                departmentId = query.executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return departmentId;
    }

    @Override
    public boolean modifyDepartment(Department newData) {
        aBoolean = false;
        if (session.isConnected()) {
            try {
                transaction = session.beginTransaction();
                Department oldData = (Department) session.get(Department.class, newData.getDepartmentId());

                if (newData.getName() != null)
                    oldData.setName(newData.getName());

                session.update(oldData);
                aBoolean = true;
                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return aBoolean;
    }

    @Override
    public List<Department> displayAllDepartments() {
        if (session.isConnected()) {
            try {
                transaction = session.beginTransaction();
                hql = "from Department";
                query = session.createQuery(hql);
                departmentList = query.list();
                transaction.commit();


            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return departmentList;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        DbEmployee.session = session;
    }

}
