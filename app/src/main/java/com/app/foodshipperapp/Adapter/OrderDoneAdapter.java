package com.app.foodshipperapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodshipperapp.Model.Order;
import com.app.foodshipperapp.Model.OrderItem;
import com.app.foodshipperapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderDoneAdapter extends RecyclerView.Adapter<OrderDoneAdapter.ViewHolder> {

    private List<Order> orders;
    private Context context;

    public OrderDoneAdapter(Context context, List<Order> orders) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_order_done
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_done, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.textOrderId.setText(String.valueOf(order.getOrderId()));
        String createdAt = order.getCreatedAt();

        // Định dạng cho việc phân tích chuỗi
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

        try {
            // Chuyển đổi chuỗi thành Date
            Date date = inputFormat.parse(createdAt);

            // Định dạng để lấy ngày và giờ
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

            // Lấy ngày và giờ
            String dateString = dateFormat.format(date);
            String timeString = timeFormat.format(date);

            // Gán giá trị cho TextView
            holder.textDate.setText(dateString); // Gán giá trị ngày
            holder.textTime.setText(timeString); // Gán giá trị giờ

        } catch (ParseException e) {
            e.printStackTrace();
            holder.textDate.setText("N/A");
            holder.textTime.setText("N/A");
        }

        // Tính tổng tiền của đơn hàng
        double total = 0;
        for (OrderItem item : order.getItems()) {
            total += item.getQuantity() * item.getFood().getPrice();
        }
        holder.textTotal.setText(String.format("$%.2f", total));
        // Lấy số từ chuỗi deliveryTime, ví dụ "15 min"
        String deliveryTime = order.getDeliveryTime(); // "15 min"
        String timeInMinutes = "0"; // Giá trị mặc định nếu không thể tách
        if (deliveryTime != null && deliveryTime.contains(" ")) {
            timeInMinutes = deliveryTime.split(" ")[0]; // Lấy "15"
        }

        try {
            // Chuyển đổi timeInMinutes sang số nguyên
            int minutes = Integer.parseInt(timeInMinutes);

            // Tính km từ phút (giả sử 1 phút = 1 km)
            int distanceInKm = minutes;

            // Tính thu nhập: 3000 VND cho mỗi km
            int incomeVND = distanceInKm * 3000;

            // Gán thu nhập vào TextView
            holder.textIncome.setText(String.format(Locale.getDefault(), "%,d VND", incomeVND));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            holder.textIncome.setText("N/A"); // Gán giá trị mặc định nếu có lỗi
        }

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Khai báo các view trong item_order_done
        TextView textOrderId, textTotal, textTime, textDate, textIncome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ các view trong layout item_order_done.xml
            textOrderId = itemView.findViewById(R.id.textOrderId);
            textTotal = itemView.findViewById(R.id.textTotal);
            textTime = itemView.findViewById(R.id.textTime);
            textDate = itemView.findViewById(R.id.textDate);
            textIncome = itemView.findViewById(R.id.textIncome);
        }
    }
}
