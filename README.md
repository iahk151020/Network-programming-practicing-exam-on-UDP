# Network-programming-practicing-exam-on-UDP
Build a client-server system with mordern MVC model about managing Employee

- Giao thức: UDP Socket
+ Server:
- Chứa thông tin công nhân bao gồm: Họ tên, Năm sinh, Quê quán, giới tính, hệ số lương
- Dữ liệu lưu trong Hai bảng: CongNhan và QueQuan, trong đó kết nối Quê Quán - Công nhân là N-1 (1 tỉnh có nhiều công nhân).
+ Client: 
- Có giao diện để thực hiện chức năng:
+ Thêm công nhân vào CSDL, trong đó mục Quê Quán phải để người dùng chọn
+ Tìm kiếm một Công nhân theo tên (gần đúng)
+ Liệt kê các công nhân thuộc cùng một tỉnh (Đồng hương).
Yêu cầu: 
1.  Xây dựng lớp Congnhan và thiết lập bảng trên CSDL MySQL cho yêu cầu trên
2. Xây dựng các phương thức của Server và Client để thực hiện các yêu cầu trên
3. Cài đặt và chạy thành công các yêu cầu: Thêm Công nhân, Tìm công nhân, Liệt kê đồng hương
Gợi ý:
- Khi khởi tạo, Client cần gửi yêu cầu lên server để lấy danh sách các tỉnh và thêm vào Danh sách chọn trên giao diện của Client
- Xây dựng lớp thông điệp (Message) có thuộc lưu trữ dữ liệu và thuộc tính phân biệt loại thông điệp
