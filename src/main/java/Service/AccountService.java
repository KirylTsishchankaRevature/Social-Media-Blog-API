package Service;

import Model.Account;
import DAO.AccountDAO;



public class AccountService {
    private AccountDAO accountDAO;
    
    //no-args constructor for creating a new AccountService with a new AccountDAO.
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    
    /**
     * Constructor for a AccountService when a AccountDAO is provided.
     * @param accountDAO
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    


    /**
     *@param account an account object.
     * @return The persisted account if the persistence is successful.
     */
    public Account addAccount(Account account) {
        return accountDAO.insertAccount(account);
    }

    /**
     * @param account an account object.
     * @return The persisted account if the persistence is successful.
     */
    public Account userLogin(Account account) {
        return accountDAO.uLogin(account);
    }
}
