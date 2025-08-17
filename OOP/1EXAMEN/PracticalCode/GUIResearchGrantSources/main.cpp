#include "GUIResearchGrant.h"
#include <QtWidgets/QApplication>

#include "RepoResearcher.h"
#include "RepoIdea.h"
#include "Service.h"
#include "Model.h"

int main(int argc, char* argv[])
{
    QApplication a(argc, argv);


    auto repoResearchers = new RepoResearcher{ "researchers.txt" };
    auto repoIdeas = new RepoIdea{"ideas.txt"};
    auto service = new Service{ *repoIdeas, *repoResearchers };

    vector<GUIResearchGrant*> windows;

    auto researchers = repoResearchers->getResearchers();

    

    for (auto r : researchers)
    {
        auto model = new Model{ *service };

        auto window = new GUIResearchGrant{ *service, model };

        window->setWindowTitle(QString::fromStdString(r.getName()));
        window->setLabel(r.getPosition());
        if (r.getPosition() != "senior")
            window->setVisible();


        windows.push_back(window);

        window->show();
    }

    return a.exec();
}
