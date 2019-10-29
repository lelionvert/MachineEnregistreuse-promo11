namespace CaisseEnregistreuse
{
    public interface IPriceQuery
    {
        Result FindPrice(string itemCode);
    }
}