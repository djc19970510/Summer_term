package cn.edu.zucc.ding.summerterm.Icontrol;


import cn.edu.zucc.ding.summerterm.model.Customer;

import java.util.List;

public interface ICustomerControl {
    public List<Customer> loadAllCustomer();
    public void modifyCustomer(Customer customer);
    public void addCustomer(Customer customer);
    public void delCustomer(Customer customer);
}
