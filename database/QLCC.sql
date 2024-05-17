create database quanlychungcu;
drop database quanlychungcu;
use quanlychungcu;
select * from taikhoan;
select distinct hd.MaHopDong,cd.TenCuDan,hd.MaCanHo,hd.MaCuDan,hd.DiaChiKH,ch.Gia,hd.NgayGiaoDich
                from HOPDONG hd inner join CANHO ch on hd.MaCanHo=ch.MaCanHo 
                inner join CUDAN cd on hd.MaCuDan=cd.MaCuDan;
select * from hopdong;

update hopdong set NgayGiaoDich = '2022-05-31' where MaHopDong = 'HD0000000001';
update hopdong set NgayGiaoDich = '2022-05-30' where MaHopDong = 'HD0000000002';
update hopdong set NgayGiaoDich = '2022-05-29' where MaHopDong = 'HD0000000003';
Alter table CuDan 
modify GioiTinh nvarchar(10);
update CuDan set GioiTinh = 'Nam' where MaCuDan = '111111';
update CuDan set GioiTinh = 'Nam' where MaCuDan = '111112';
update CuDan set GioiTinh = 'Nữ' where MaCuDan = '111113';
select * from taikhoan;
select * from cudan;
select * from canho;

select * from HOPDONG;
select * from HOPDONG hd join CANHO ch on hd.MaCanHo=ch.MaCanHo;
select  * from HOPDONG hd join CANHO ch on hd.MaCanHo=ch.MaCanHo 
		where hd.MaHopDong like 'TS0103%' or hd.NgayGiaodich like 'TS0103%'
                 or hd.DiaChiKH like 'TS0103%' or hd.MaCuDan like '%TS0103%' 
                 or hd.MaCanHo like '%TS0103%' or ch.Gia like 'TS0103%';
				
select distinct hd.MaHopDong,cd.TenCuDan,hd.MaCanHo,hd.MaCuDan,hd.DiaChiKH,ch.Gia,hd.NgayGiaoDich
                from HOPDONG hd inner join CANHO ch on hd.MaCanHo=ch.MaCanHo 
                inner join CUDAN cd on hd.MaCuDan=cd.MaCuDan 
                where hd.MaHopDong like '%TS0103%' or hd.NgayGiaodich like '%TS0103%'
                 or hd.DiaChiKH like '%TS0103%' or hd.MaCuDan like '%TS0103%' 
                 or hd.MaCanHo like '%TS0103%' or cd.TenCuDan like '%TS0103%' or ch.Gia like 'TS0103%';
                 
select * from CUDAN ;
delete  from CUDAN;
INSERT INTO cudan VALUES (N'111114', N'Nguyễn Thanh Nguyên', CAST(N'1999-04-20' AS Date), 'Nam', N'0123498765', N'01234567123', N'TPHCM');


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
INSERT INTO canho VALUES (N'TS2301', 64, 2000000000, 0, 4, NULL, N'TS');
INSERT INTO canho VALUES (N'TS2502', 84, 2000000000, 0, 5, NULL, N'TS');
INSERT INTO canho VALUES (N'TS2607', 55, 1000000000, 0, 3, NULL, N'AA');

INSERT INTO canho VALUES (N'TS2079', 63, 1000000000, 0, 3, NULL, N'TS');
INSERT INTO canho VALUES (N'TS0102', 49, 1000000000, 0, 5, NULL, N'CE');


INSERT INTO hopdong VALUES (N'HD0000000001', CAST(N'2019-03-30' AS Date), N'Số 20, Phạm Văn Đồng, Bắc Từ Liêm', N'111111', N'TS0103');
INSERT INTO hopdong VALUES (N'HD0000000002', CAST(N'2019-03-30' AS Date), N'Số 50, Trần Duy Hưng, Cầu Giấy', N'111112', N'TS2502');
INSERT INTO hopdong VALUES (N'HD0000000003', CAST(N'2019-03-30' AS Date), N'Văn Trì, Từ Liêm, Hà Nội', N'111113', N'TS2301');

select * from cudan;
select * from canho;
select * from khucanho;
select * from hopdong;
select MaCanHo, DienTich, Gia, TrangThai, SoPhong, TenKhu from CanHo ch, KhuCanHo kch where ch.MaKhu = kch.MaKhu;
select * from TaiKhoan;
select * from hopdong;	


USE quanlychungcu;

select cd.MaCuDan, TenCuDan, NgaySinh, GioiTinh
from CuDan cd, CanHo ch
where ch.MaCuDan = cd.MaCuDan and ch.MaCanHo = 'TS0103';

drop table CTCD;
create table CTCD (
	MaCuDan int,
    FOREIGN KEY(MaCuDan) REFERENCES CuDan(MaCuDan),
    MaCanHo CHAR(6),
	FOREIGN KEY(MaCanHo) REFERENCES CanHo(MaCanHo),
    PRIMARY KEY(MaCuDan,MaCanHo)
);

select * from CTCD;

insert into CTCD VALUES(111111,'TS0103');
insert into CTCD VALUES(111112,'TS2502');
insert into CTCD VALUES(111113,'TS2301');
insert into CTCD VALUES(111114,'TU0201');
insert into CTCD VALUES(111115,'TS2502');
insert into CTCD VALUES(111116,'TS2607');


select cd.MaCuDan, TenCuDan, NgaySinh, GioiTinh, SoDT, SoCMT, QueQuan
from CTCD CT, CuDan cd, CanHo ch
where  CT.MaCuDan = cd.MaCuDan and CT.MaCanHo = ch.MaCanHo and ch.MaCanHo = 'TS0103'


