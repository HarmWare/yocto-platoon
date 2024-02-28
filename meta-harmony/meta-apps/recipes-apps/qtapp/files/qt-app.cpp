#include <QApplication>
#include <QLabel>
#include <QWidget>
#include <QDebug>
int main(int argc, char *argv[])
{
    QApplication app(argc, argv);
    QLabel label("Hello World!");
    label.setWindowTitle("My First Qt App");
    label.setAlignment(Qt::AlignCenter);
    label.resize(400, 400);
    label.show();

    return app.exec();
}
