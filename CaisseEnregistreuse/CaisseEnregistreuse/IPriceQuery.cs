namespace CashRegister
{
    public interface IPriceQuery
    {
        Result FindPrice(string itemCode);
    }
}