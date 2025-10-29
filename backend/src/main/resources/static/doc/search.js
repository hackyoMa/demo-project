let api = [];
const apiDocListSize = 1
api.push({
    name: 'default',
    order: '1',
    list: []
})
api[0].list.push({
    alias: 'LicenseController',
    order: '1',
    link: 'licensecontroller',
    desc: 'LicenseController',
    list: []
})
api[0].list[0].list.push({
    order: '1',
    deprecated: 'false',
    url: '/api/license/current',
    methodId: 'e0b43068f4727eeeb2eb1f4f49d5b1b6',
    desc: 'get current license',
});
api[0].list.push({
    alias: 'PermissionController',
    order: '2',
    link: 'permissioncontroller',
    desc: 'PermissionController',
    list: []
})
api[0].list[1].list.push({
    order: '1',
    deprecated: 'false',
    url: '/api/permission',
    methodId: 'ce72026ec54c822ae8eafeeaebfab896',
    desc: 'get permission list',
});
api[0].list.push({
    alias: 'RoleController',
    order: '3',
    link: 'rolecontroller',
    desc: 'RoleController',
    list: []
})
api[0].list[2].list.push({
    order: '1',
    deprecated: 'false',
    url: '/api/role',
    methodId: '8a40f1d0fbc28f66bf9cc0a8dcc0ba83',
    desc: 'get role list',
});
api[0].list[2].list.push({
    order: '2',
    deprecated: 'false',
    url: '/api/role',
    methodId: '735d8136f0cbdb13f312208d5b8388d1',
    desc: 'add role',
});
api[0].list[2].list.push({
    order: '3',
    deprecated: 'false',
    url: '/api/role',
    methodId: 'b1898239b41023f9b53689dcbcd2d646',
    desc: 'update role',
});
api[0].list[2].list.push({
    order: '4',
    deprecated: 'false',
    url: '/api/role/{roleId}',
    methodId: '16db08a7bc7ce888676b47ffc47a0cb5',
    desc: 'delete role',
});
api[0].list[2].list.push({
    order: '5',
    deprecated: 'false',
    url: '/api/role/{roleId}/permission',
    methodId: '930b7e3bad0123d8496df6aa8e856c5d',
    desc: 'get role permission list',
});
api[0].list.push({
    alias: 'OrgController',
    order: '4',
    link: 'orgcontroller',
    desc: 'OrgController',
    list: []
})
api[0].list[3].list.push({
    order: '1',
    deprecated: 'false',
    url: '/api/org',
    methodId: '393ebc0f121e34326e00af5cbbcd8fc9',
    desc: 'get org list',
});
api[0].list[3].list.push({
    order: '2',
    deprecated: 'false',
    url: '/api/org',
    methodId: '679eeb097a21d3d61378b711cbcf3f4c',
    desc: 'add org',
});
api[0].list[3].list.push({
    order: '3',
    deprecated: 'false',
    url: '/api/org',
    methodId: 'a3b82a6869e50d7da156bd0853c0ebc5',
    desc: 'update org',
});
api[0].list[3].list.push({
    order: '4',
    deprecated: 'false',
    url: '/api/org/{orgId}',
    methodId: '875c727487753be36ecd2ead022c536c',
    desc: 'delete org',
});
api[0].list[3].list.push({
    order: '5',
    deprecated: 'false',
    url: '/api/org/{orgId}/user/{page}/{pageSize}',
    methodId: '43f12c0a474382ea56368e03ffc40e76',
    desc: 'get org user list - paged',
});
api[0].list[3].list.push({
    order: '6',
    deprecated: 'false',
    url: '/api/org/{orgId}/user/not_exits',
    methodId: '002ebb149b1318df10258e1f264801fa',
    desc: 'get not exits org user',
});
api[0].list[3].list.push({
    order: '7',
    deprecated: 'false',
    url: '/api/org/{orgId}/user',
    methodId: 'cb882fc74c3b23554d38d90b091eeebc',
    desc: 'add org user',
});
api[0].list[3].list.push({
    order: '8',
    deprecated: 'false',
    url: '/api/org/{orgId}/user/{userId}',
    methodId: 'ff649fa5016774fbe6272b4028b9581f',
    desc: 'delete org user',
});
api[0].list.push({
    alias: 'UserController',
    order: '5',
    link: 'usercontroller',
    desc: 'UserController',
    list: []
})
api[0].list[4].list.push({
    order: '1',
    deprecated: 'false',
    url: '/api/user/_login',
    methodId: 'fa608c93406150e7c5afbcebab3d9822',
    desc: 'user login',
});
api[0].list[4].list.push({
    order: '2',
    deprecated: 'false',
    url: '/api/user/current',
    methodId: 'f94d599b93eb2e575b300f6a7b963160',
    desc: 'get current user',
});
api[0].list[4].list.push({
    order: '3',
    deprecated: 'false',
    url: '/api/user/current',
    methodId: '1f1e605cee35a832d5d2bb57ac80923c',
    desc: 'update current user',
});
api[0].list[4].list.push({
    order: '4',
    deprecated: 'false',
    url: '/api/user/current/password',
    methodId: '6d3623d631c0e1aa4e093758aa2bddd7',
    desc: 'update current user password',
});
api[0].list[4].list.push({
    order: '5',
    deprecated: 'false',
    url: '/api/user/{page}/{pageSize}',
    methodId: 'bef88759f3d2f0e8eb458998f37374a3',
    desc: 'get user list - paged',
});
api[0].list[4].list.push({
    order: '6',
    deprecated: 'false',
    url: '/api/user',
    methodId: '374a9add91220af2016842ac7d42b412',
    desc: 'add user',
});
api[0].list[4].list.push({
    order: '7',
    deprecated: 'false',
    url: '/api/user',
    methodId: '1b863c7b7f54282c4f7704e10520f17e',
    desc: 'update user',
});
api[0].list[4].list.push({
    order: '8',
    deprecated: 'false',
    url: '/api/user/{userId}',
    methodId: '322a7778bcf01800cdfa86c9f0710332',
    desc: 'delete user',
});
api[0].list.push({
    alias: 'SysConfigController',
    order: '6',
    link: 'sysconfigcontroller',
    desc: 'SysConfigController',
    list: []
})
api[0].list[5].list.push({
    order: '1',
    deprecated: 'false',
    url: '/api/sys-config',
    methodId: 'b1111d20b095ae57afaf2f86e553b0d9',
    desc: 'get sys config',
});
api[0].list[5].list.push({
    order: '2',
    deprecated: 'false',
    url: '/api/sys-config',
    methodId: '151641ce92f9b3e26db99ca69e4ba5af',
    desc: 'update sys config',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
        const search = document.getElementById('search');
        const searchValue = search.value.toLocaleLowerCase();

        let searchGroup = [];
        for (let i = 0; i < api.length; i++) {

            let apiGroup = api[i];

            let searchArr = [];
            for (let i = 0; i < apiGroup.list.length; i++) {
                let apiData = apiGroup.list[i];
                const desc = apiData.desc;
                if (desc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                    searchArr.push({
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        alias: apiData.alias,
                        list: apiData.list
                    });
                } else {
                    let methodList = apiData.list || [];
                    let methodListTemp = [];
                    for (let j = 0; j < methodList.length; j++) {
                        const methodData = methodList[j];
                        const methodDesc = methodData.desc;
                        if (methodDesc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                            methodListTemp.push(methodData);
                            break;
                        }
                    }
                    if (methodListTemp.length > 0) {
                        const data = {
                            order: apiData.order,
                            desc: apiData.desc,
                            link: apiData.link,
                            alias: apiData.alias,
                            list: methodListTemp
                        };
                        searchArr.push(data);
                    }
                }
            }
            if (apiGroup.name.toLocaleLowerCase().indexOf(searchValue) > -1) {
                searchGroup.push({
                    name: apiGroup.name,
                    order: apiGroup.order,
                    list: searchArr
                });
                continue;
            }
            if (searchArr.length === 0) {
                continue;
            }
            searchGroup.push({
                name: apiGroup.name,
                order: apiGroup.order,
                list: searchArr
            });
        }
        let html;
        if (searchValue === '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchGroup,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            let $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiGroups, liClass, display) {
    let html = "";
    if (apiGroups.length > 0) {
        if (apiDocListSize === 1) {
            let apiData = apiGroups[0].list;
            let order = apiGroups[0].order;
            for (let j = 0; j < apiData.length; j++) {
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#' + apiData[j].alias + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                html += '<ul class="sectlevel2" style="'+display+'">';
                let doc = apiData[j].list;
                for (let m = 0; m < doc.length; m++) {
                    let spanString;
                    if (doc[m].deprecated === 'true') {
                        spanString='<span class="line-through">';
                    } else {
                        spanString='<span>';
                    }
                    html += '<li><a href="#' + doc[m].methodId + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                }
                html += '</ul>';
                html += '</li>';
            }
        } else {
            for (let i = 0; i < apiGroups.length; i++) {
                let apiGroup = apiGroups[i];
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_'+apiGroup.order+'_' + apiGroup.name + '">' + apiGroup.order + '.&nbsp;' + apiGroup.name + '</a>';
                html += '<ul class="sectlevel1">';

                let apiData = apiGroup.list;
                for (let j = 0; j < apiData.length; j++) {
                    html += '<li class="'+liClass+'">';
                    html += '<a class="dd" href="#' + apiData[j].alias + '">' +apiGroup.order+'.'+ apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                    html += '<ul class="sectlevel2" style="'+display+'">';
                    let doc = apiData[j].list;
                    for (let m = 0; m < doc.length; m++) {
                       let spanString;
                       if (doc[m].deprecated === 'true') {
                           spanString='<span class="line-through">';
                       } else {
                           spanString='<span>';
                       }
                       html += '<li><a href="#' + doc[m].methodId + '">'+apiGroup.order+'.' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                   }
                    html += '</ul>';
                    html += '</li>';
                }

                html += '</ul>';
                html += '</li>';
            }
        }
    }
    return html;
}