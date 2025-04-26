import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

class Item implements Serializable{

    String item_code;
    String item_name;
    double unit_price;
    double per_discount;

    Item(String item_code, String item_name, double unit_price, double per_discount){
        this.item_code = item_code;
        this.item_name = item_name;
        this.unit_price = unit_price;
        this.per_discount = per_discount;

    }

}

class pos implements Serializable{
    static HashMap<String,Item> data = new HashMap<>();
    
     static void readfile(String filename) throws IOException{
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line ;
        br.readLine();
        //System.out.println(line);
        while((line = br.readLine()) != null){
            String[] line_data = line.split(",");
            //System.out.println(line_data);
            Item item = new Item(line_data[0],line_data[1],Double.parseDouble(line_data[2]),Double.parseDouble(line_data[7]));
            data.put(line_data[0],item);
            //System.out.println(data.get(1).item_name.toString());
        }

    }

    static void savebill(Bill bill) throws IOException{
        FileWriter fw = new FileWriter("bill.txt",true);
        BufferedWriter bw = new  BufferedWriter(fw);
        List<List<Object>> item_bill = new ArrayList<>();
        item_bill = bill.item_bill;
        bw.write(bill.cashier_name+"\n");
        bw.write(bill.customer_name+"\n");
        
        for (List<Object> bill_item:item_bill){
            Item item = (Item) bill_item.get(0);
            double discount = (double) bill_item.get(1);
            double net_price = (double) bill_item.get(2);
           
            bw.write(item.item_code+" "+item.item_name+" "+Double.toString(discount)+" "+ Double.toString(net_price)+"\n");
    }
    bw.write(Double.toString(bill.total_discount)+"\n");
    bw.write(Double.toString(bill.final_net_price)+"\n");
    bw.close();
}
    static void serialize(Bill bill) throws IOException{
        FileOutputStream fout_s = new FileOutputStream("pending_bill.ser");
        ObjectOutputStream objout = new ObjectOutputStream(fout_s);
        objout.writeObject(bill);
        objout.close();
        
    }

    static Bill deserialize() throws IOException, ClassNotFoundException{
        FileInputStream fin_s = new FileInputStream("pending_bill.ser");
        ObjectInputStream objin = new ObjectInputStream(fin_s);
        Object bill1 = objin.readObject();
        Bill bill2 = (Bill) bill1;
        objin.close();
        return bill2;
    }
}

class Bill implements Serializable{
    String cashier_name;
    String customer_name;
    double unit_price ,gross_price,discount,net_price,total_discount,final_net_price;
    List<List<Object>> item_bill = new ArrayList<>();
    
    //Double Discount;
    //Double Net_Price;

    Bill(String cash_name, String cust_name){
        this.cashier_name = cash_name;
        this.customer_name = cust_name;
    }

    void addtobill(Item item, double quantity){
        List<Object> per_item = new ArrayList<>();
        unit_price = item.unit_price;
        gross_price = unit_price* quantity;
        discount = gross_price*item.per_discount/100.00;
        net_price = gross_price-discount;
        //Discount = discount;
        //Net_Price = net_price;
        per_item.add(item);
        per_item.add(discount);
        per_item.add(net_price);
        item_bill.add(per_item);

    }

    void printbill(){
        for (List<Object> bill_item:item_bill){
            Item item = (Item) bill_item.get(0);
            double discount = (double) bill_item.get(1);
            double net_price = (double) bill_item.get(2);
            total_discount += discount;
            final_net_price += net_price;
            System.out.println(item.item_name+" has been given a "+discount+" discount. Net price is "+net_price);
        }
        System.out.println("Total discount: "+total_discount);
        System.out.println("Total net price: "+final_net_price);
    }

    
}
public class pos_ implements Serializable{
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        while(true){
        System.out.println("\n");
        System.out.println("Select one: ");
        System.out.println("1.New bill");
        System.out.println("2.Pending bill");
        Scanner scanner5 = new Scanner(System.in);
        String selection = scanner5.nextLine();

        Bill bill = null;
        if (selection.equals("1")){
        System.out.print("Enter Cashier name: ");
        Scanner scanner1 = new Scanner(System.in);
        String cashier_name = scanner1.nextLine();
        System.out.print("Enter customer name: ");
        Scanner scanner2 = new Scanner(System.in);
        String customer_name = scanner2.nextLine();
        bill = new Bill(cashier_name,customer_name);
        }
        else if (selection.equals("2")){
            bill = pos.deserialize();
        }
        
        //System.out.println(customer_name);
        
        pos.readfile("super_saving_items.csv");
        boolean wantsave = true;
        
        while(true){
            try{
            System.out.print("Enter the item code: ");
            Scanner scanner3 = new Scanner(System.in);
            String item_code = scanner3.nextLine();
            //System.out.println(item_code);
            if (item_code == ""){
                break;
            }
            else if (item_code.equals("save")){
                pos.serialize(bill);
                wantsave = false;
                break;
                //pos_.main(args);
                //System.out.println("\n"+"resume pending bill");
                //continue;
                
            }
            if (!pos.data.containsKey(item_code)){
                System.out.println("Item not found");
                continue;
            }
            Item item_data = pos.data.get(item_code);
            //System.out.println(item_data.item_name);
            
            System.out.print("Enter quantity: ");
            Scanner scanner4 = new Scanner(System.in);
            double quantity = scanner4.nextDouble();
            bill.addtobill(item_data,quantity);

            }
            catch (Exception e){
                //System.out.println("Item not found");
            }
           

        }
        if (wantsave){
            bill.printbill();
            pos.savebill(bill);
            }
            //else if (selection.equals("2")){
                
            //1}        
        
        }
    }
}