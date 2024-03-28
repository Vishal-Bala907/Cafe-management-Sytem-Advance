module org.CafeManageMent.CafeManageSys {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.persistence;
	requires org.hibernate.orm.core;
	requires java.sql;
	requires java.naming;
	requires java.desktop;
	requires java.xml;

//	requires hibernate.core;
//	requires com.h2database.h2;

	opens org.CafeManageMent.CafeManageSys to javafx.fxml;
	opens com.dataBases to org.hibernate.orm.core;

	exports org.CafeManageMent.CafeManageSys;
}
