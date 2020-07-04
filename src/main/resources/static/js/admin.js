var flag=0;
var page=1;
var pageAll;
$(function () {

    $('#WOdate').datetimepicker({
        format: 'YYYY-MM-DD',

        locale: moment.locale('zh-cn')
    });
    $('#Sdate').datetimepicker({
        format: 'YYYY-MM-DD',

        locale: moment.locale('zh-cn')
    });


        $(".dropdown-toggle").dropdown('toggle');

    var locale = {
        "format": 'YYYY-MM-DD',
        "separator": " 至 ",
        "applyLabel": "确定",
        "cancelLabel": "取消",
        "fromLabel": "起始时间",
        "toLabel": "结束时间'",
        "weekLabel": "W",
        "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
        "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        "firstDay": 1
    };
    //日期控件初始化
    $('#daterange').daterangepicker(
        {
            locale: locale,
            startDate: moment().subtract(29, 'days'),
            endDate: moment(),
            autoUpdateInput: false
        },
        function (start, end) {
            $('#daterangeC').html(start.format('YYYY-MM-DD') + ' 至 ' + end.format('YYYY-MM-DD'));
        }
    );
});
function change(index) {
    var sel=$("select:eq("+index+")").val();
    if (sel==0) {
        $('#confirm').hide();
        $('#WOfood').css('cssText', 'display:none !important');
        $('#WOmoney').css('cssText', 'display:none !important');
    }
    if(sel==1){
        $('#WOmoney').css('cssText', 'height:40px; border:#DDD solid 1px;display:flex');
        $('#WOfood').css('cssText', 'height:40px; border:#DDD solid 1px;display:none !important');
        $('#confirm').show();
        flag=1;
    }
    if(sel==2){
        $('#WOfood').css('cssText', 'height:40px; border:#DDD solid 1px;display:flex');
        $('#WOmoney').css('cssText', 'height:40px; border:#DDD solid 1px;display:none !important');
        $('#confirm').show();
        flag=2;
    }
}
function searchWO() {
    var date=$('#WOdate').val();
    $.ajax({
        url:"/workOvertime/"+date,
        type:"get",
        success: function (data) {
            var WO=data;
            if(WO!=""&&WO!=null){
               $('select:eq(0)').val(WO.type);
                var date1 = new Date(WO.data);
                var date2 = new Date();
                var days=Math.floor((date1.getTime()-date2.getTime())/(3600*1000));
               if(WO.type==1){
                   $('#WOmoney').css('cssText', 'height:40px; border:#DDD solid 1px;display:flex');
                   $('#money').val(WO.award);
                   $('#WOfood').css('cssText', 'height:40px; border:#DDD solid 1px;display:none !important');
                   if(days<=7){
                       $('select:eq(0)').attr("disabled","disabled");
                       $('#money').attr("readonly","readonly");
                       $('#confirm').hide();
                       $('#put').hide();
                   }else {
                       $('#put').show();
                       $('#confirm').hide();
                   }
                   flag=1;
               }else{
                   $('#WOfood').css('cssText', 'height:40px; border:#DDD solid 1px;display:flex');
                   $('#WOfood').val(WO.award);
                   $('#WOmoney').css('cssText', 'height:40px; border:#DDD solid 1px;display:none !important');
                   if(days<=7){
                       $('select:eq(0)').attr("disabled","disabled")
                       $('select:eq(1)').attr("disabled","disabled");
                       $('#confirm').hide();
                       $('#put').hide();
                   }else {
                       $('#put').show();
                       $('#confirm').hide();
                   }
                   flag=2;
               }
            }else{
                $('select:eq(0)').removeAttr("disabled");
                $('select:eq(1)').removeAttr("disabled");
                $('select:eq(0)').val(0)
                $('#money').removeAttr("readonly");
                $('#confirm').hide();
                $('#put').hide();
                $('#WOfood').css('cssText', 'display:none !important');
                $('#WOmoney').css('cssText', 'display:none !important');
            }
        },
        error: function () {
            alert("查询失败!!!");
        }
    })
}
function confirmWO() {
    var award;
    if (flag == 1) {
        award = $('#money').val();
    }
    if (flag == 2) {
        award = $('select:eq(1)').val();
    }
    $.ajax({
        url: "/workOvertime",
        type: "post",
        data: JSON.stringify({
            "award": award,
            "data": $('#WOdate').val(),
            "type": $('select:eq(0)').val()
        }),
        contentType: "application/json",
        success: function (data) {
            alert("添加成功!!!");
        },
        error: function () {
            alert("添加失败!!!");
        }

    })
}
    function putWO() {
        var award;
        if (flag == 1) {
            award = $('#money').val();
        }
        if (flag == 2) {
            award = $('select:eq(1)').val();
        }
        console.log()
        $.ajax({
            url: "/workOvertime",
            type: "patch",
            data: JSON.stringify({
                "award": award,
                "data": $('#WOdate').val(),
                "type": $('select:eq(0)').val()
            }),
            contentType: "application/json",
            success: function (data) {
                alert("修改成功!!!");
            },
            error: function () {
                alert("修改失败!!!");
            }

        })
    }
function searchUser(nextPage){
    $.ajax({
        url:"/workOvertime/user/"+$('#Sdate').val()+"/"+nextPage,
        type:"get",
        success: function (data) {
            $('#userList').html("");
            pageAll=data[0];
            let users=data[1];
            let type=['','加班费','加班餐'];
            var userList="";
            for(let i in data){
           userList+="<tr>\n" +
                "                                   <td> "+(i*1+1)+" </td> <td>"+users[i].date+"</td> <td> "+users[i].name+"</td> <td>"+users[i].phone+"</td> <td>"+type[users[i].type]+"</td> <td>"+users[i].log_award+"</td>\n" +
                "                               </tr>";
            }
            $('#userList').html(userList);
            $('#WOS .align-self-end').remove();
            $('#WOS').append(setPage("searchUser",pageAll,nextPage));
            changePage("WOS",nextPage);
        },
        error: function () {
            alert("查询失败!!!");
        }
    })
}
function searchExa(nextPage){
$.ajax({
    url:"/workOvertime/logs/"+$('#daterangeC').text()+"/"+nextPage,
    type:"get",
    success: function (data) {
        $('#exaList').html("");
     pageAll=data[0];
      var logs=data[1];
      var str="";
      for(let i in logs){
          var date1 = new Date(logs[i].date);
          var date2 = new Date();
          var days=Math.floor((date1.getTime()-date2.getTime())/(3600*1000));
          console.log(days);
        switch (logs[i].log_state) {
            case 0:

                if(days>-24) {
                    str += "<tr >\n" +
                        "                                   <td valign=\"middle\">" + logs[i].date + "</td> <td valign=\"middle\"> " + logs[i].name + "</td> <td valign=\"middle\">" + logs[i].phone + "</td> <td valign=\"middle\">" + logs[i].log_award + "</td>\n" +
                        "                                   <td valign=\"middle\"> <button class=\"btn btn-default rounded mr-3 pt-1 pb-1 badge-primary\" style=\"color:#FFF;\" onclick='putLogs(" + logs[i].log_id + ",1)'>同意</button> <button class=\"btn btn-default rounded pt-1 pb-1 badge-danger\" style=\"color:#FFF;\" onclick='putLogs(" + logs[i].log_id + ",2)'>拒绝</button></td>\n" +
                        "                               </tr>";
                }else{
                    str += "<tr >\n" +
                        "                                   <td valign=\"middle\">" + logs[i].date + "</td> <td valign=\"middle\"> " + logs[i].name + "</td> <td valign=\"middle\">" + logs[i].phone + "</td> <td valign=\"middle\">" + logs[i].log_award + "</td>\n" +
                        "                                   <td style=\"color: #6c757d;font-weight: bold;\"> 未审核 </td>\n" +
                        "                               </tr>";
                }
                break;
            case 1:
                str+="<tr>\n" +
                    "                                   <td>"+logs[i].date+"</td> <td> "+logs[i].name+"</td> <td>"+logs[i].phone+"</td> <td>"+logs[i].log_award+"</td>\n" +
                    "                                   <td style=\"color: #2e4250;font-weight: bold;\"> 已同意 </td>\n" +
                    "                               </tr>";
                break;
            case 2:
                str+="<tr>\n" +
                    "                                   <td>"+logs[i].date+"</td> <td> "+logs[i].name+"</td> <td>"+logs[i].phone+"</td> <td>"+logs[i].log_award+"</td>\n" +
                    "                                   <td style=\"color: #2e4250;font-weight: bold;\"> 已拒绝 </td>\n" +
                    "                               </tr>";
                break;
        }
      }
        $('#exaList').html(str);
        $('#WOexa .align-self-end').remove();
        $('#WOexa').append(setPage("searchExa",pageAll,nextPage));
        changePage("WOexa",nextPage);
    },
    error: function () {
        alert("查询失败!!!");
    }
})
}

function putLogs(logId,state) {
   $.ajax({
       url:"/workOvertime/logs/"+logId+"/"+state,
       type:"put",
       success: function (data) {
           alert("操作成功！");
           searchExa(1);
       },
       error: function () {
           alert("查询失败!!!");
       }

   })

}
function setPage(method,pageAll,nextPage) {           //建立分页
    str="<div class=\"bg-white m-4 align-self-end\" style=\"font-size:16px;\">\n" +
        "\t\t\t\t<nav>\n" +
        "\t\t\t\t<ul class=\"pagination\">";
    if(pageAll>6){
        str=str+" <li class=\"page-item\" onclick=\""+method+"('"+(nextPage*1-1)+"')\">\n" +
            "                          <a class=\"page-link\" style='color: #2e4250'>pre</a>\n" +
            "                      </li>";
        for(var i=1;i<=5;i++){
            str=str+" <li class=\"page-item\" onclick=\""+method+"('"+i+"')\">\n" +
                "                          <a class=\"page-link\" style='color: #2e4250'>"+i+"</a>\n" +
                "                      </li>";
        }
        str=str+" <li class=\"page-item\" >\n" +
            "                          <p class=\"page-link\" style='color: #2e4250'>...</p>\n" +
            "                      </li>";
        str=str+" <li class=\"page-item\"  onclick=\""+method+"('"+pageAll+"')\">\n" +
            "                          <a class=\"page-link\"  style='color: #2e4250'>"+pageAll+"</a>\n" +
            "                      </li>";
        str=str+" <li class=\"page-item\"  onclick=\""+method+"('"+(nextPage*1+1)+"')\">\n" +
            "                          <a class=\"page-link\" style='color: #2e4250'>next</a>\n" +
            "                      </li>";
    }else {
        if (pageAll >1) {
            str=str+" <li class=\"page-item\" onclick=\""+method+"('"+(nextPage*1-1)+"')\">\n" +
                "                          <a class=\"page-link\" style='color: #2e4250'>pre</a>\n" +
                "                      </li>";}
        for (var i = 1; i <= pageAll; i++) {
            str=str+" <li class=\"page-item\"  onclick=\""+method+"('"+i+"')\">\n" +
                "                          <a class=\"page-link\" style='color: #2e4250'>" + i + "</a>\n" +
                "                      </li>";
        }
        if (pageAll > 1) {
            str=str+" <li class=\"page-item\"  onclick=\""+method+"('"+(nextPage*1+1)+"')\">\n" +
                "                          <a class=\"page-link\" style='color: #2e4250'>next</a>\n" +
                "                      </li>";
        }
        str=str+"</ul>\n" +
            "\t\t\t</nav>\n" +
            "\t\t\t</div>";
    }
    return str;
}
function changePage(id,p) {     //换页
    $("#"+id+" .pagination a:eq("+page*1+")").removeClass("pageA");
    page=p;
    if(page==1){
        $("#"+id+" .pagination a:eq("+(page*1-1)+")").parent().addClass('pageB');
    }else{
        $("#"+id+" .pagination a:eq("+(page*1-1)+")").parent().removeClass("pageB");
    }
    if(page>5&&page<pageAll){
        if(page*1==pageAll*1){
            $("#" + id + " .pagination a:eq(" + (page * 1-1) + ")").addClass('pageA');
            $("#"+id+" .pagination a:eq("+(page*1)+")").parent().addClass('pageB');
        }
        $("#"+id+" .pagination p").addClass("pageA");
        $("#"+id+" .pagination p").html(page);
    }else{
        if(page==pageAll){
            if (pageAll>6) {
                $("#" + id + " .pagination a:eq(" + (page * 1 - 1) + ")").addClass('pageA');
                $("#" + id + " .pagination a:eq(" + (page * 1) + ")").parent().addClass('pageB');
            }else{
                $("#" + id + " .pagination a:eq(" + (page * 1) + ")").addClass('pageA');
                $("#" + id + " .pagination a:eq(" + (page * 1+1) + ")").parent().addClass('pageB');
            }
        }else{
            $("#"+id+" .pagination a:eq("+page*1+")").addClass('pageA');
        }
        if (pageAll>6) {
            $("#" + id + " .pagination p").removeClass("pageA");
            $("#" + id + " .pagination p").html("...");
        }
    }
}