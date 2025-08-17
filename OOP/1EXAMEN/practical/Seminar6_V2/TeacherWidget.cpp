#include "TeacherWidget.h"

TeacherWidget::TeacherWidget(QAbstractItemModel* s, QWidget *parent)
	: QWidget(parent)
{
	ui.setupUi(this);
	ui.studentsTabelView->setModel(s);
}

TeacherWidget::~TeacherWidget()
{}
