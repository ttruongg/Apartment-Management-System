create database quanlychungcu;
use quanlychungcu;
CREATE TABLE TAIKHOAN
    (
      TenTaiKhoan VARCHAR(25)  PRIMARY KEY ,
      MatKhau VARCHAR(16) NOT NULL ,
      VaiTro BIT NOT NULL
                 DEFAULT 0
    );



CREATE TABLE KHUCANHO
    (
      MaKhu CHAR(2) PRIMARY KEY,
      TenKhu NVARCHAR(50) NOT NULL ,
      SoTang INT NOT NULL
                 DEFAULT 1 ,
      SoCanTT INT NOT NULL
                  DEFAULT 1 ,
      DiaChi NVARCHAR(100) NOT NULL
	);


CREATE TABLE CUDAN
    (
      MaCuDan int  PRIMARY KEY ,
      TenCuDan NVARCHAR(50) NOT NULL ,
      NgaySinh DATE NOT NULL,
                    
      GioiTinh VARCHAR(10) NOT NULL
                   DEFAULT 0 ,
      SoDT CHAR(10) NOT NULL ,
      SoCMT CHAR(12) NOT NULL ,
      QueQuan NVARCHAR(100) NOT NULL
    );



CREATE TABLE CANHO
    (
      MaCanHo CHAR(6)  PRIMARY KEY ,
      DienTich FLOAT NOT NULL
                     DEFAULT 50 ,
      Gia BIGINT NOT NULL ,
      TrangThai BIT NOT NULL
                    DEFAULT 0 ,
      SoPhong INT NOT NULL
                  DEFAULT 5 ,
      MaCuDan int,
         FOREIGN KEY (MaCuDan) REFERENCES cudan(MaCuDan),
      MaKhu CHAR(2)
        NOT NULL
        DEFAULT 'AA',
        
        FOREIGN KEY (MaKhu) REFERENCES khucanho ( MaKhu ) ON DELETE CASCADE
        ON UPDATE CASCADE --  (AA -> ZZ)
    ) ;



CREATE TABLE HOPDONG
    (
      MaHopDong CHAR(12) NOT NULL
                          PRIMARY KEY , -- HD0000000001
      NgayGiaoDich DATE NOT NULL,
                        
      DiaChiKH NVARCHAR(100) NOT NULL ,
      MaCuDan int
        NOT NULL,
		FOREIGN KEY (MaCuDan) REFERENCES cudan( MaCuDan ) ,
      MaCanHo CHAR(6)
        NOT NULL,
        
        FOREIGN KEY (MaCanHo) REFERENCES canho ( MaCanHo ) ON DELETE CASCADE
        ON UPDATE CASCADE
    );
    
    
    INSERT INTO TAIKHOAN   VALUES (N'Admin', N'123456', 1);
INSERT INTO TAIKHOAN   VALUES (N'NV001', N'abc123', 0);
INSERT INTO TAIKHOAN   VALUES (N'NV002', N'xyz123', 0);



INSERT INTO khucanho VALUES (N'AA', N'An Phú', 25, 20, N'Hà Đông');
INSERT INTO khucanho VALUES (N'CE', N'Hi Land', 30, 25, N'Cầu Giấy');
INSERT INTO khucanho VALUES (N'TS', N'The Spark', 25, 20, N'Hà Đông');
INSERT INTO khucanho VALUES (N'PS', N'Phú Sinh', 10, 5, N'TPHCM');



INSERT INTO cudan VALUES (N'111111', N'Trần Văn Nam', CAST(N'1998-05-21' AS Date), 'Nam', N'0123456789', N'012345678985', N'Yên Bái');
INSERT INTO cudan VALUES (N'111112', N'Nguyễn Văn An', CAST(N'1997-02-25' AS Date), 'Nam', N'0123456789', N'012365897456', N'Nam Định');
INSERT INTO cudan VALUES (N'111113', N'Phạm Thị Nguyên Hồng', CAST(N'1991-02-28' AS Date), 'Nữ', N'0123658974', N'000256398745', N'Hà Nội');







INSERT INTO canho VALUES (N'TS0103', 50, 1200000000, 1, 5, N'111111', N'TS');
INSERT INTO canho VALUES (N'TS2301', 50, 2000000000, 0, 4, NULL, N'TS');
INSERT INTO canho VALUES (N'TS2502', 50, 2000000000, 0, 5, NULL, N'TS');
INSERT INTO canho VALUES (N'TS2607', 50, 1000000000, 0, 3, NULL, N'AA');



INSERT INTO hopdong VALUES (N'HD0000000001', CAST(N'2019-03-30' AS Date), N'Số 20, Phạm Văn Đồng, Bắc Từ Liêm', N'111111', N'TS0103');
INSERT INTO hopdong VALUES (N'HD0000000002', CAST(N'2019-03-30' AS Date), N'Số 50, Trần Duy Hưng, Cầu Giấy', N'111112', N'TS2502');
INSERT INTO hopdong VALUES (N'HD0000000003', CAST(N'2019-03-30' AS Date), N'Văn Trì, Từ Liêm, Hà Nội', N'111113', N'TS2301');
