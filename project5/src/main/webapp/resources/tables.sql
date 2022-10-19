create sequence seq_board;

create table tbl_board (
    bno number(10,0),
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate
);

alter table tbl_board add constraint pk_board primary key (bno);

insert into tbl_board (bno, title, content, writer) values (seq_board.nextval, '제목11111', '내용11111', 'user11111');
insert into tbl_board (bno, title, content, writer) values (seq_board.nextval, '제목22222', '내용22222', 'user22222');
insert into tbl_board (bno, title, content, writer) values (seq_board.nextval, '제목33333', '내용33333', 'user33333');  

select * from tbl_board where bno > 0;

SELECT bno, title, content, writer, regdate, updatedate 
			FROM (
					SELECT /* +INDEX_DESC(tbl_board pk_board) */ rownum rn, bno, title, content, writer, regdate, updatedate 
					FROM tbl_board 
					/* WHERE rownum <= #{pageNum} * #{amount} */
                    WHERE rownum <= 5
					)
			/* WHERE rn > (#{pageNum} -1) * #{amount} */
            WHERE rn > 0
;
-- 댓글 처리를 위한 테이블 설계
create table tbl_reply (
    rno number(10, 0),
    bno number(10, 0) not null,
    reply varchar2(1000) not null,
    replyer varchar2(50) not null,
    replyDate date default sysdate,
    updateDate date default sysdate    
);
create sequence seq_reply;
alter table tbl_reply add constraint pk_reply primary key (rno);
alter table tbl_reply  add constraint fk_reply_board foreign key (bno) references tbl_board (bno); 