(function () {

  var $usernameFld, $passwordFld;
  var $firstNameFld, $lastNameFld, $roleFld;
  var $removeBtn, $editBtn, $createBtn;
  var $userRowTemplate, $tbody;
  var userService =  new AdminUserServiceClient()


  $(main)

  var users = [
    // {username: "ada", password: "", firstName: "Ada", lastName: "Lovelace", role: "Faculty"},
    // {username: "sfa", password: "", firstName: "Bo", lastName: "Chen", role: "Faculty"},
  ]

  function main() {
    $userRowTemplate = $("#wbdv-tbody")
    $usernameFld = $("#usernameFld")
    $passwordFld = $("#passwordFld")
    $firstNameFld = $("#firstNameFld")
    $lastNameFld = $("#lastNameFld")
    $roleFld = $("#roleFld")

    $editBtn = $(".wbdv-edit")
    $createBtn = $(".wbdv-create")
    $removeBtn = $(".wbdv-remove")

    findAllUsers()

    // $createBtn.click(createUser)
    // userService.findAllUsers().then(function (allUsers) {
    //   users = allUsers
    //   renderUsers(users)
    // })

  }

  function renderUsers(users) {
    $userRowTemplate.empty()
    for (var i=0; i < users.length; i++) {
      var user = users[i]
      $userRowTemplate.append(`
        <tr class="wbdv-template wbdv-user wbdv-hidden">
          <td class="wbdv-username">${user.username}</td>
          <td>&nbsp;</td>
          <td class="wbdv-first-name">${user.firstName}</td>
          <td class="wbdv-last-name">${user.lastName}</td>
          <td class="wbdv-role">${user.role}</td>
          <td class="wbdv-actions" style="white-space: nowrap">
            <button class="float-right">
              <i id="${i}" class="wbdv-remove fa fa fa-times wbdv-remove wbdv-icon"></i>
            </button>
          </td>
          <td class="wbdv-actions" style="white-space: nowrap">
            <button class="float-right">
              <i id="${i}" class="wbdv-edit fa fa fa-pencil wbdv-edit wbdv-icon"></i>
            </button>
          </td>
        </tr>
      `
      )
    }
    $(".wbdv-remove").click(deleteUser)
    $(".wbdv-edit").click(selectUser)


  }

  function createUser() {
    var newUser = {
      username: $usernameFld.val(),
      password: $passwordFld.val(),
      firstName: $firstNameFld.val(),
      lastName: $lastNameFld.val(),
      role: $roleFld.val()
    }

    userService.createUser(newUser).then(function (actualUser) {
      users.push(actualUser)
      renderUsers(users)
    })


  }


  function deleteUser(event) {
    var button = $(event.target)
    var index = button.attr("id")
    var theUserId = users[index]._id
    console.log(theUserId)

    userService.deleteUser(theUserId).then(function (status){
      users.splice(index, 1)
      renderUsers(users)
    })
  }

  function selectUser(event) {
    var id = $(event.target).attr("id")
    var selectedUser = users[id]
    $usernameFld.val(selectedUser.username)
    $passwordFld.val(selectedUser.password)
    $firstNameFld.val(selectedUser.firstName)
    $lastNameFld.val(selectedUser.lastName)
    $roleFld.val(selectedUser.role)

  }

  function findAllUsers() {
    userService.findAllUsers().then(function (allUsers) {
      users = allUsers
      renderUsers(users)
    })
  }


}) ();